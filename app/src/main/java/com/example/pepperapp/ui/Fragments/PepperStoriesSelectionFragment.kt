package com.example.pepperapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pepperapp.R

class PepperStoriesSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_pepper_stories_selection, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.buttonMoumouteWithEmotion).setOnClickListener {
            val bundle = androidx.core.os.bundleOf("storyId" to 1, "withEmotion" to true)
            findNavController().navigate(R.id.action_pepperStoriesSelectionFragment_to_listenStoryFragment, bundle)
        }

        view.findViewById<Button>(R.id.buttonMoumouteNoEmotion).setOnClickListener {
            val bundle = androidx.core.os.bundleOf("storyId" to 1, "withEmotion" to false)
            findNavController().navigate(R.id.action_pepperStoriesSelectionFragment_to_listenStoryFragment, bundle)
        }

        view.findViewById<Button>(R.id.buttonFootWithEmotion).setOnClickListener {
            val bundle = androidx.core.os.bundleOf("storyId" to 3, "withEmotion" to true)
            findNavController().navigate(R.id.action_pepperStoriesSelectionFragment_to_listenStoryFragment, bundle)
        }

        view.findViewById<Button>(R.id.buttonFootNoEmotion).setOnClickListener {
            val bundle = androidx.core.os.bundleOf("storyId" to 3, "withEmotion" to false)
            findNavController().navigate(R.id.action_pepperStoriesSelectionFragment_to_listenStoryFragment, bundle)
        }

        view.findViewById<Button>(R.id.buttonBack).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
