package com.example.pepperapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pepperapp.R
import com.example.pepperapp.data.SessionManager

class SurveyAvantFragment : Fragment() {

    private lateinit var spinnerAge: Spinner
    private lateinit var radioGroupKnown: RadioGroup
    private lateinit var questionsContainer: LinearLayout
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button

    private val radioGroups = mutableListOf<RadioGroup>()

    private val avantQuestions = arrayOf(
        "1. Penses-tu que ce robot sera amical ?",
        "2. Penses-tu que ce robot rendra l'histoire intéressante ?",
        "3. Penses-tu que ce robot montrera des sentiments en racontant l'histoire ?",
        "4. Penses-tu que ce robot comprendra les sentiments dans l'histoire ?",
        "5. Penses-tu que ce robot aura l'air vivant ?",
        "6. Penses-tu que ce robot aura l'air réel ?",
        "7. Penses-tu que tu auras l'impression que ce robot est vraiment avec toi ?",
        "8. Penses-tu que ce robot sera intelligent ?",
        "9. Penses-tu que ce robot pourrait être ton ami ?",
        "10. Penses-tu que tu voudras entendre une autre histoire de ce robot ?"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_avant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerAge = view.findViewById(R.id.spinnerAgeAvant)
        radioGroupKnown = view.findViewById(R.id.rgKnownStory)
        questionsContainer = view.findViewById(R.id.questionsContainerAvant)
        btnNext = view.findViewById(R.id.btnNextAvant)
        btnBack = view.findViewById(R.id.btnBackAvant)

        // Setup Age Spinner
        val ages = (3..12).toList().map { it.toString() }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAge.adapter = adapter

        // Setup 10 Questions Dynamically
        val options = arrayOf("Pas du tout", "Un peu", "Oui", "Beaucoup")
        
        for ((index, questionText) in avantQuestions.withIndex()) {
            val tv = TextView(context).apply {
                text = questionText
                textSize = 18f
                setTypeface(null, android.graphics.Typeface.BOLD)
                setTextColor(android.graphics.Color.BLACK)
                setPadding(0, 32, 0, 16)
            }
            questionsContainer.addView(tv)

            val rg = RadioGroup(context).apply {
                orientation = RadioGroup.HORIZONTAL
            }
            
            for ((optIndex, opt) in options.withIndex()) {
                val rb = RadioButton(context).apply {
                    text = opt
                    textSize = 16f
                    id = View.generateViewId()
                    setPadding(8, 8, 32, 8)
                    // Tag holds the score (1 to 4)
                    tag = optIndex + 1 
                }
                rg.addView(rb)
            }
            questionsContainer.addView(rg)
            radioGroups.add(rg)
        }

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnNext.setOnClickListener {
            // Validate all questions answered
            if (radioGroupKnown.checkedRadioButtonId == -1) {
                Toast.makeText(context, "Veuillez indiquer si vous connaissez l'histoire.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            for ((i, rg) in radioGroups.withIndex()) {
                if (rg.checkedRadioButtonId == -1) {
                    Toast.makeText(context, "Veuillez répondre à la question ${i + 1}.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            // Save data to SessionManager
            val age = spinnerAge.selectedItem?.toString()?.toIntOrNull() ?: 0
            val known = radioGroupKnown.checkedRadioButtonId == R.id.rbKnownYes

            SessionManager.age = age
            SessionManager.knownStory = known

            for ((i, rg) in radioGroups.withIndex()) {
                val selectedId = rg.checkedRadioButtonId
                val rb = rg.findViewById<RadioButton>(selectedId)
                val score = rb.tag as Int
                // Store as q1, q2...
                SessionManager.perceptionAvant["q${i + 1}"] = score
            }

            // Navigate to StoryComprehensionFragment
            findNavController().navigate(R.id.action_surveyAvantFragment_to_storyComprehensionFragment)
        }
    }
}
