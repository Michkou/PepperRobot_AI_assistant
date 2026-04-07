package com.example.pepperapp.ui.Fragments

import android.animation.ObjectAnimator
import android.view.animation.CycleInterpolator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
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
import com.aldebaran.qi.sdk.builder.HolderBuilder
import com.aldebaran.qi.sdk.`object`.holder.AutonomousAbilitiesType
import com.example.pepperapp.R
import com.example.pepperapp.data.StoryRepository

class ListenStoryFragment : Fragment(), RobotLifecycleCallbacks {

    private lateinit var tvSection: TextView
    private lateinit var bgImage: ImageView
    private var qiContext: QiContext? = null
    private var currentFuture: com.aldebaran.qi.Future<Void>? = null
    private var isCancelled = false
    private val localeFR = Locale(Language.FRENCH, Region.FRANCE)
    private var currentStoryLines: List<String> = emptyList()
    private var awarenessHolder: com.aldebaran.qi.sdk.`object`.holder.Holder? = null
    private var awarenessHoldFuture: com.aldebaran.qi.Future<Void>? = null

    // Animations for Story 2 (Mammouth)
    private val animationsStory2 = listOf(
        "animations/05-Enumeration/Enumeration_01.qianim",
        "animations/02-Body_Parts/Show_Hand_Right_01.qianim",
        "animations/06-Solitaries/Funny_01.qianim",
        "animations/08-Attract/Attract_L01.qianim",
        "animations/07-Reactions/SadReaction_01.qianim",
        "animations/07-Reactions/SadReaction_02.qianim",
        "animations/08-Attract/Attract_R01.qianim",
        "animations/05-Enumeration/Enumeration_02.qianim",
        "animations/02-Body_Parts/Show_Hand_Both_01.qianim",
        "animations/06-Solitaries/LookAtSidesLeft_01.qianim",
        "animations/06-Solitaries/LookAtSidesRight_01.qianim",
        "animations/07-Reactions/SadReaction_01.qianim",
        "animations/07-Reactions/SadReaction_02.qianim",
        "animations/08-Attract/Attract_L02.qianim",
        "animations/06-Solitaries/Funny_02.qianim",
        "animations/02-Body_Parts/Show_Hand_Left_01.qianim",
        "animations/08-Attract/Attract_R02.qianim",
        "animations/05-Enumeration/Enumeration_03.qianim",
        "animations/06-Solitaries/Looking_around_01.qianim",
        "animations/01-Hello/Hello_01.qianim"
    )

    // Images for Story 2
    private val imagesStory2 = listOf(
        R.drawable.story2_scene_01,
        R.drawable.story2_scene_02,
        R.drawable.story2_scene_03,
        R.drawable.story2_scene_04,
        R.drawable.story2_scene_05,
        R.drawable.story2_scene_06,
        R.drawable.story2_scene_07,
        R.drawable.story2_scene_08,
        R.drawable.story2_scene_09,
        R.drawable.story2_scene_10,
        R.drawable.story2_scene_11,
        R.drawable.story2_scene_12,
        R.drawable.story2_scene_13,
        R.drawable.story2_scene_14,
        R.drawable.story2_scene_15,
        R.drawable.story2_scene_16,
        R.drawable.story2_scene_17,
        R.drawable.story2_scene_18,
        R.drawable.story2_scene_19,
        R.drawable.story2_scene_20
    )

    // Images for Story 3
    private val imagesStory3 = listOf(
        R.drawable.story3_scene_01,
        R.drawable.story3_scene_02,
        R.drawable.story3_scene_03,
        R.drawable.story3_scene_04,
        R.drawable.story3_scene_05,
        R.drawable.story3_scene_06,
        R.drawable.story3_scene_07,
        R.drawable.story3_scene_08,
        R.drawable.story3_scene_09,
        R.drawable.story3_scene_10,
        R.drawable.story3_scene_11,
        R.drawable.story3_scene_12,
        R.drawable.story3_scene_13,
        R.drawable.story3_scene_14,
        R.drawable.story3_scene_15,
        R.drawable.story3_scene_16,
        R.drawable.story3_scene_17,
        R.drawable.story3_scene_18,
        R.drawable.story3_scene_19,
        R.drawable.story3_scene_20,
        R.drawable.story3_scene_21,
        R.drawable.story3_scene_22
    )

    // Images for Story 4
    private val imagesStory4 = listOf(
        R.drawable.story4_scene_01,
        R.drawable.story4_scene_02,
        R.drawable.story4_scene_03,
        R.drawable.story4_scene_04,
        R.drawable.story4_scene_05,
        R.drawable.story4_scene_06,
        R.drawable.story4_scene_07,
        R.drawable.story4_scene_08,
        R.drawable.story4_scene_09,
        R.drawable.story4_scene_10,
        R.drawable.story4_scene_11,
        R.drawable.story4_scene_12,
        R.drawable.story4_scene_13,
        R.drawable.story4_scene_14,
        R.drawable.story4_scene_15,
        R.drawable.story4_scene_16,
        R.drawable.story4_scene_17,
        R.drawable.story4_scene_18,
        R.drawable.story4_scene_19,
        R.drawable.story4_scene_20,
        R.drawable.story4_scene_21,
        R.drawable.story4_scene_22,
        R.drawable.story4_scene_23,
        R.drawable.story4_scene_24,
        R.drawable.story4_scene_25,
        R.drawable.story4_scene_26
    )

    // Images for Story 4 (ID 4: Helmouth a perdu sa confiance)
    private val imagesStory1 = listOf(
        R.drawable.story1_scene_01,
        R.drawable.story1_scene_02,
        R.drawable.story1_scene_03,
        R.drawable.story1_scene_04,
        R.drawable.story1_scene_05,
        R.drawable.story1_scene_06,
        R.drawable.story1_scene_07,
        R.drawable.story1_scene_08,
        R.drawable.story1_scene_09,
        R.drawable.story1_scene_10,
        R.drawable.story1_scene_11,
        R.drawable.story1_scene_12,
        R.drawable.story1_scene_13,
        R.drawable.story1_scene_14,
        R.drawable.story1_scene_15,
        R.drawable.story1_scene_16,
        R.drawable.story1_scene_17,
        R.drawable.story1_scene_18,
        R.drawable.story1_scene_19
    )

    // Animations for Story 4 (Foot)
    private val animationsStory4 = listOf(
        "animations/01-Hello/Hello_03.qianim",
        "animations/06-Solitaries/Looking_around_wide_01.qianim",
        "animations/05-Enumeration/Enumeration_01.qianim",
        "animations/06-Solitaries/LookFarLeft_01.qianim",
        "animations/08-Attract/Attract_R05.qianim",
        "animations/02-Body_Parts/Show_Hand_Right_02.qianim",
        "animations/02-Body_Parts/Show_Hand_Left_01.qianim",
        "animations/07-Reactions/SadReaction_01.qianim",
        "animations/05-Enumeration/Enumeration_02.qianim",
        "animations/06-Solitaries/LookBumpersRight_01.qianim",
        "animations/06-Solitaries/LookBumpersLeft_01.qianim",
        "animations/05-Enumeration/Enumeration_03.qianim",
        "animations/07-Reactions/SadReaction_02.qianim",
        "animations/06-Solitaries/LookHandLeft_01.qianim",
        "animations/07-Reactions/SadReaction_01.qianim",
        "animations/07-Reactions/SadReaction_02.qianim", // "On a perdu à cause de toi..." (Stay sad)
        "animations/07-Reactions/SadReaction_01.qianim", // "En rentrant chez lui Helmouth pleure..." (Stay sad)
        "animations/06-Solitaries/LookFarRight_01.qianim",
        "animations/08-Attract/Attract_R06.qianim",
        "animations/05-Enumeration/Enumeration_04.qianim",
        "animations/05-Enumeration/Enumeration_01.qianim",
        "animations/06-Solitaries/Funny_01.qianim",
        "animations/06-Solitaries/Funny_02.qianim",
        "animations/02-Body_Parts/Show_Hand_Both_01.qianim",
        "animations/01-Hello/Hello_04.qianim"
    )

    // Fonction pour faire trembler l'image (Choc / Moquerie)
    private fun shakeImage(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", -20f, 20f)
        animator.duration = 500 // Tremble pendant 0.5s
        animator.interpolator = CycleInterpolator(5f) // Fait 5 allers-retours
        animator.start()
    }

    // Fonction pour faire un zoom arrière lent (Solitude / Vide)
    private fun zoomOutImage(view: View) {
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1.2f, 1.0f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1.2f, 1.0f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(view, scaleX, scaleY)
        animator.duration = 8000 // Zoom très lent sur 8 secondes
        animator.start()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_listen_story, container, false)
        view.findViewById<android.widget.Button>(R.id.buttonBack).setOnClickListener {
            isCancelled = true
            currentFuture?.requestCancellation()
            findNavController().popBackStack()
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        QiSDK.register(requireActivity(), this)
    }

    override fun onPause() {
        QiSDK.unregister(requireActivity(), this)
        super.onPause()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvSection = view.findViewById(R.id.tvStorySection)
        bgImage = view.findViewById(R.id.bgListenStory)

        // Retrieve storyId from arguments (default to 1)
        val storyId = arguments?.getInt("storyId") ?: 1
        val story = StoryRepository.getStoryById(storyId)

        story?.let {
            // Update background
            bgImage.setImageResource(it.backgroundResId)
            
            // Split text content into chunks/sentences for "scrolling" feel
            // We split by newline for paragraphs or simple chunks
            currentStoryLines = it.textContent.split("\n\n", ".\n").filter { p -> p.isNotBlank() }
            
            // Initial text
            tvSection.text = it.title
        }
    }

    override fun onRobotFocusGained(context: QiContext) {
        qiContext = context
        isCancelled = false

        Thread {
            try {
                // Retrieve storyId again
                val storyId = arguments?.getInt("storyId") ?: 1
                
                // Disable BasicAwareness for story 2 & 4 so Pepper stays completely still
                if (storyId == 2 || storyId == 4) {
                    try {
                        val holder = HolderBuilder.with(context)
                            .withAutonomousAbilities(AutonomousAbilitiesType.BASIC_AWARENESS)
                            .build()
                        awarenessHoldFuture = holder.async().hold()
                        awarenessHolder = holder
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

                // Select animations list
                val currentAnimations = when(storyId) {
                    1 -> animationsStory2
                    2 -> emptyList<String>()
                    3 -> animationsStory4
                    4 -> emptyList<String>()
                    else -> emptyList<String>()
                }
                
                val currentImages = when(storyId) {
                    1 -> imagesStory2
                    2 -> imagesStory3
                    3 -> imagesStory4
                    4 -> imagesStory1
                    else -> emptyList()
                }
                
                for (index in currentStoryLines.indices) {
                    if (isCancelled || qiContext == null) return@Thread
                    
                    val line = currentStoryLines[index]
                    
                    // Regex to remove tags like \pau=500\, \vct=120\, \rspd=80\ for the visual display
                    val cleanLine = line.replace(Regex("""\\[a-z]+=\d+\\"""), "").replace(Regex("""\\[a-z]+=\d+"""), "").replace("\\", "").trim()
                    
                    // Update text UI using View.post to update on main thread
                    tvSection.post {
                        tvSection.text = cleanLine
                        currentImages.getOrNull(index)?.let { resId ->
                            bgImage.setImageResource(resId)
                            
                            // --- Effets d'empathie visuelle ---
                            if (storyId == 3) {
                                // Index 11 : "Ce que tu peux être lourd ! Quel boulet !" -> Tremblement de moquerie
                                if (index == 11) shakeImage(bgImage)
                                
                                // Index 13 or 14 : Helmouth est envoyé dans les buts, seul -> Zoom arrière (Recul de solitude)
                                if (index == 13 || index == 14) zoomOutImage(bgImage)
                            } else if (storyId == 1) {
                                // Index 3: Tempête emporte la touffe -> Choc / Tremblement
                                if (index == 3) shakeImage(bgImage)
                                
                                // Index 5: Helmouth seul, rejeté, très triste -> Solitude / Zoom arrière
                                if (index == 5) zoomOutImage(bgImage)
                                
                                // Index 8 à 12: Tous les animaux explosent de rire tour à tour -> Tremblement
                                if (index in 8..12) shakeImage(bgImage)
                            }
                        }
                    }

                    if (isCancelled || qiContext == null) return@Thread

                    val sayBuilder = SayBuilder.with(context)
                        .withLocale(localeFR)
                        .withText(line)

                    // Désactiver le langage corporel (mouvements des mains) pour l'histoire 2 et 4
                    if (storyId == 2 || storyId == 4) {
                        sayBuilder.withBodyLanguageOption(com.aldebaran.qi.sdk.`object`.conversation.BodyLanguageOption.DISABLED)
                    }

                    val sayAction = sayBuilder.build()

                    val fSay = sayAction.async().run()
                    currentFuture = fSay
                    fSay.get()

                    if (isCancelled || qiContext == null) return@Thread

                    // Animation
                    currentAnimations.getOrNull(index)?.let { path ->
                        try {
                            val anim: Animation = AnimationBuilder.with(context)
                                .withAssets(path)
                                .build()
                            val fAnim = AnimateBuilder.with(context)
                                .withAnimation(anim)
                                .build()
                                .async().run()
                            currentFuture = fAnim
                            fAnim.get()
                        } catch (e: Exception) {
                            // ignore missing animation
                        }
                    }
                }
            } catch (e: Exception) {
                // Prevent crash if one job fails (e.g. "Robot is already talking" or "Focus lost")
                if (e !is java.util.concurrent.CancellationException) {
                    e.printStackTrace()
                }
            }
        }.start()
    }

    override fun onRobotFocusLost() {
        isCancelled = true
        currentFuture?.requestCancellation()
        
        // Release BasicAwareness hold
        awarenessHoldFuture?.requestCancellation()
        awarenessHolder = null
        
        qiContext = null
    }

    override fun onRobotFocusRefused(reason: String?) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
