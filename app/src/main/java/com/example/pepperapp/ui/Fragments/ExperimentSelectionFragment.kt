package com.example.pepperapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pepperapp.R

class ExperimentSelectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_experiment_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navigateToNext = { condition: String ->
            // Initialiser le SessionManager
            com.example.pepperapp.data.SessionManager.reset()
            com.example.pepperapp.data.SessionManager.condition = condition
            
            // Générer un idSession avec le temps actuel + la condition (ex: 12345EL1)
            val dateFormat = java.text.SimpleDateFormat("mmssSSS", java.util.Locale.getDefault())
            val timeStr = dateFormat.format(java.util.Date())
            com.example.pepperapp.data.SessionManager.idSession = timeStr + condition
            
            findNavController().navigate(R.id.action_experimentSelectionFragment_to_surveyAvantFragment)
        }

        view.findViewById<Button>(R.id.btn_H1_EL1).setOnClickListener { navigateToNext("EL1") }
        view.findViewById<Button>(R.id.btn_H1_NL1).setOnClickListener { navigateToNext("NL1") }
        view.findViewById<Button>(R.id.btn_H1_EO1).setOnClickListener { navigateToNext("EO1") }
        view.findViewById<Button>(R.id.btn_H1_NO1).setOnClickListener { navigateToNext("NO1") }

        view.findViewById<Button>(R.id.btn_H2_EL2).setOnClickListener { navigateToNext("EL2") }
        view.findViewById<Button>(R.id.btn_H2_NL2).setOnClickListener { navigateToNext("NL2") }
        view.findViewById<Button>(R.id.btn_H2_EO2).setOnClickListener { navigateToNext("EO2") }
        view.findViewById<Button>(R.id.btn_H2_NO2).setOnClickListener { navigateToNext("NO2") }

        view.findViewById<Button>(R.id.buttonBackExperiment).setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
