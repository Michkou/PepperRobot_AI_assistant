package com.example.pepperapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pepperapp.R
import com.example.pepperapp.data.SessionManager
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.`object`.locale.Language
import com.aldebaran.qi.sdk.`object`.locale.Locale
import com.aldebaran.qi.sdk.`object`.locale.Region
import com.example.pepperapp.ui.MainActivity
import kotlinx.coroutines.launch

class StoryComprehensionFragment : Fragment() {

    private lateinit var questionText: TextView
    private lateinit var optionLayouts: List<LinearLayout>
    private lateinit var optionIcons: List<ImageView>
    private lateinit var optionTexts: List<TextView>

    private var currentIndex = 0
    private var questions: List<com.example.pepperapp.data.Question> = emptyList()
    private var sayFuture: com.aldebaran.qi.Future<Void>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Determiner l'histoire (H1 -> 1, H2 -> 3)
        val isStory1 = SessionManager.condition.endsWith("1")
        val storyId = if (isStory1) 1 else 3
        
        val story = com.example.pepperapp.data.StoryRepository.getStoryById(storyId)
        questions = story?.questions ?: emptyList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_story_comprehension, container, false)
        
        questionText = view.findViewById(R.id.questionTextComp)

        optionLayouts = listOf(
            view.findViewById(R.id.optionLayoutA_Comp),
            view.findViewById(R.id.optionLayoutB_Comp),
            view.findViewById(R.id.optionLayoutC_Comp),
            view.findViewById(R.id.optionLayoutD_Comp),
            view.findViewById(R.id.optionLayoutE_Comp),
            view.findViewById(R.id.optionLayoutF_Comp)
        )

        optionIcons = listOf(
            view.findViewById(R.id.optionIconA_Comp),
            view.findViewById(R.id.optionIconB_Comp),
            view.findViewById(R.id.optionIconC_Comp),
            view.findViewById(R.id.optionIconD_Comp),
            view.findViewById(R.id.optionIconE_Comp),
            view.findViewById(R.id.optionIconF_Comp)
        )

        optionTexts = listOf(
            view.findViewById(R.id.optionTextA_Comp),
            view.findViewById(R.id.optionTextB_Comp),
            view.findViewById(R.id.optionTextC_Comp),
            view.findViewById(R.id.optionTextD_Comp),
            view.findViewById(R.id.optionTextE_Comp),
            view.findViewById(R.id.optionTextF_Comp)
        )

        optionLayouts.forEachIndexed { idx, layout ->
            layout.setOnClickListener { onOptionSelected(idx) }
        }

        showQuestion()

        return view
    }

    private fun showQuestion() {
        if (currentIndex >= questions.size) return

        val original = questions[currentIndex]
        questionText.text = original.text

        val shuffled = original.options.shuffled()
        val correctLabel = original.options[original.correctIndex].first
        val newCorrectIndex = shuffled.indexOfFirst { it.first == correctLabel }

        questions = questions.toMutableList().apply {
            this[currentIndex] = com.example.pepperapp.data.Question(original.text, shuffled, newCorrectIndex)
        }

        optionLayouts.forEach { it.visibility = View.GONE }
        val optionsTextList = mutableListOf<String>()

        shuffled.forEachIndexed { i, (label, iconResId) ->
            if (i < optionLayouts.size) {
                val layout = optionLayouts[i]
                val iconView = optionIcons[i]
                val textView = optionTexts[i]
                
                layout.visibility = View.VISIBLE
                textView.text = label
                iconView.setImageResource(iconResId)
                optionsTextList.add(label)
                
                layout.isEnabled = true
            }
        }

        val choicesText = optionsTextList.joinToString(separator = ", ou ")
        val speechText = "${original.text} Les choix sont : $choicesText."
        speakConcurrently(speechText)
    }

    private fun speakConcurrently(text: String) {
        sayFuture?.requestCancellation()
        (activity as? MainActivity)?.getQiContext()?.let { ctx ->
            lifecycleScope.launch(kotlinx.coroutines.Dispatchers.IO) {
                try {
                    val say = SayBuilder.with(ctx)
                        .withLocale(Locale(Language.FRENCH, Region.FRANCE))
                        .withText(text)
                        .build()
                    sayFuture = say.async().run()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun onOptionSelected(selected: Int) {
        if (currentIndex >= questions.size) return

        val isCorrect = if (selected == questions[currentIndex].correctIndex) 1 else 0
        SessionManager.comprehensionHistoire.add(isCorrect)
        
        currentIndex++

        if (currentIndex < questions.size) {
            showQuestion()
        } else {
            finishComprehension()
        }
    }

    private fun finishComprehension() {
        optionLayouts.forEach { it.isEnabled = false }
        sayFuture?.requestCancellation()
        
        findNavController().navigate(R.id.action_storyComprehensionFragment_to_surveyApresFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sayFuture?.requestCancellation()
    }
}
