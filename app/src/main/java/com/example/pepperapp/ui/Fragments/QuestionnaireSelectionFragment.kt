package com.example.pepperapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pepperapp.R

class QuestionnaireSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_questionnaire_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()

        // Button 1 logic -> GameFragment with storyId=1
        view.findViewById<Button>(R.id.buttonStory1).setOnClickListener {
            val bundle = bundleOf("storyId" to 1)
            navController.navigate(R.id.action_questionnaireSelectionFragment_to_gameFragment, bundle)
        }

        // Button 2 logic
        view.findViewById<Button>(R.id.buttonStory2).setOnClickListener {
            val bundle = bundleOf("storyId" to 2)
            navController.navigate(R.id.action_questionnaireSelectionFragment_to_gameFragment, bundle)
        }

        // Button 3 logic
        view.findViewById<Button>(R.id.buttonStory3).setOnClickListener {
            val bundle = bundleOf("storyId" to 3)
            navController.navigate(R.id.action_questionnaireSelectionFragment_to_gameFragment, bundle)
        }

        // Button 4 logic
        view.findViewById<Button>(R.id.buttonStory4).setOnClickListener {
            val bundle = bundleOf("storyId" to 4)
            navController.navigate(R.id.action_questionnaireSelectionFragment_to_gameFragment, bundle)
        }

        // Back button logic
        view.findViewById<Button>(R.id.buttonBack).setOnClickListener {
            navController.popBackStack()
        }
    }
}
