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

        view.findViewById<Button>(R.id.buttonStory1WithEmotion).setOnClickListener {
            val bundle = androidx.core.os.bundleOf("storyId" to 1)
            findNavController().navigate(R.id.action_pepperStoriesSelectionFragment_to_listenStoryFragment, bundle)
        }

        view.findViewById<Button>(R.id.buttonStory2NoEmotion).setOnClickListener {
            val bundle = androidx.core.os.bundleOf("storyId" to 2)
            findNavController().navigate(R.id.action_pepperStoriesSelectionFragment_to_listenStoryFragment, bundle)
        }

        view.findViewById<Button>(R.id.buttonBack).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
