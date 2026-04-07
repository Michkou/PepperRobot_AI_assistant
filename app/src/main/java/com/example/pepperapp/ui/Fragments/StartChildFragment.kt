package com.example.pepperapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.`object`.actuation.Animation
import com.aldebaran.qi.sdk.`object`.locale.Language
import com.aldebaran.qi.sdk.`object`.locale.Locale
import com.aldebaran.qi.sdk.`object`.locale.Region
import com.aldebaran.qi.sdk.builder.AnimationBuilder
import com.aldebaran.qi.sdk.builder.AnimateBuilder
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.example.pepperapp.R
import com.example.pepperapp.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StartChildFragment : Fragment(), RobotLifecycleCallbacks {

    private var qiContext: QiContext? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_start_child, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (androidx.core.content.ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            QiSDK.register(requireActivity(), this)
        }
    }

    override fun onPause() {
        if (androidx.core.content.ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_GRANTED) {
            QiSDK.unregister(requireActivity(), this)
        }
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonStart).setOnClickListener {
            findNavController().navigate(R.id.action_startChildFragment_to_chooseFragment)
        }
    }

    private val activeFutures = mutableListOf<com.aldebaran.qi.Future<*>>()

    override fun onRobotFocusGained(context: QiContext) {
        qiContext = context

        // Execute in background
        Thread {
            try {
                // Wait a little bit for the previous fragment's speech/animation to strictly cancel.
                // This prevents "The robot is already talking" race conditions.
                Thread.sleep(800)
                
                if (qiContext == null) return@Thread

                val helloAnim: Animation = AnimationBuilder.with(context)
                    .withAssets("animations/01-Hello/Hello_03.qianim")
                    .build()
                val helloAction = AnimateBuilder.with(context)
                    .withAnimation(helloAnim)
                    .build()

                val text = """Bonjour les enfants! Je suis Pepper, votre robot préféré pour raconter des histoires. Aujourd'hui, je suis avec vous pour raconter de très belles histoires. Vous allez voir que vous allez adorer! Êtes-vous prêts? Je veux que vous m'écoutiez bien, car à la fin, je serai très ravi de faire votre connaissance.""".trimIndent()
                val sayAction = SayBuilder.with(context)
                    .withLocale(Locale(Language.FRENCH, Region.FRANCE))
                    .withText(text)
                    .build()

                val fHello = helloAction.async().run()
                activeFutures.add(fHello)

                val fSay = sayAction.async().run()
                activeFutures.add(fSay)
                
                // Wait for both to finish, but catch individual errors so we don't abort
                try { fHello.get() } catch (e: com.aldebaran.qi.QiException) { return@Thread } catch (e: Exception) { }
                try { fSay.get() } catch (e: com.aldebaran.qi.QiException) { return@Thread } catch (e: Exception) { }
                
                if (qiContext == null) return@Thread

                // 3) Animation "looking around"
                try {
                    val lookAnim = AnimationBuilder.with(context)
                        .withAssets("animations/06-Solitaries/Looking_around_01.qianim")
                        .build()
                    val fLook = AnimateBuilder.with(context)
                        .withAnimation(lookAnim)
                        .build()
                        .async().run()
                    activeFutures.add(fLook)
                    fLook.get()
                } catch (e: com.aldebaran.qi.QiException) {
                    return@Thread
                } catch (e: Exception) { }

                listOf("LookRight_01.qianim", "LookLeft_01.qianim").forEach { file ->
                    if (qiContext == null) return@Thread
                    try {
                        val anim = AnimationBuilder.with(context)
                            .withAssets("animations/06-Solitaries/$file")
                            .build()
                        val fAnim = AnimateBuilder.with(context)
                            .withAnimation(anim)
                            .build()
                            .async().run()
                        activeFutures.add(fAnim)
                        fAnim.get()
                    } catch (e: com.aldebaran.qi.QiException) {
                        return@Thread
                    } catch (e: Exception) { }
                }

            } catch (e: com.aldebaran.qi.QiException) {
                // Focus lost or cancelled, exit cleanly
            } catch (e: Exception) {
                // E.g. InterruptedException
                e.printStackTrace()
            }
        }.start()
    }

    override fun onRobotFocusLost() {
        activeFutures.forEach { it.requestCancellation() }
        activeFutures.clear()
        qiContext = null
    }

    override fun onRobotFocusRefused(reason: String?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
