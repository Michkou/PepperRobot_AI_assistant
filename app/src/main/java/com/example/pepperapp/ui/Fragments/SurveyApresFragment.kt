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
import com.google.firebase.firestore.FirebaseFirestore

class SurveyApresFragment : Fragment() {

    private lateinit var questionsContainer: LinearLayout
    private lateinit var btnFinish: Button
    private lateinit var btnBack: Button

    private val radioGroups = mutableListOf<RadioGroup>()

    private val apresQuestions = arrayOf(
        "1. Ce robot était-il amical ?",
        "2. Ce robot a-t-il rendu l'histoire intéressante ?",
        "3. Ce robot a-t-il montré des sentiments en racontant l'histoire ?",
        "4. Ce robot a-t-il compris les sentiments dans l'histoire ?",
        "5. Ce robot semblait-il vivant ?",
        "6. Ce robot semblait-il réel ?",
        "7. As-tu eu l'impression que ce robot était vraiment avec toi ?",
        "8. Ce robot semblait-il intelligent ?",
        "9. Ce robot pourrait-il être ton ami ?",
        "10. Aimerais-tu entendre une autre histoire du robot ?"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_survey_apres, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questionsContainer = view.findViewById(R.id.questionsContainerApres)
        btnFinish = view.findViewById(R.id.btnFinishApres)
        btnBack = view.findViewById(R.id.btnBackApres)

        val options = arrayOf("Pas du tout", "Un peu", "Oui", "Beaucoup")

        for ((index, questionText) in apresQuestions.withIndex()) {
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

        btnFinish.setOnClickListener {
            for ((i, rg) in radioGroups.withIndex()) {
                if (rg.checkedRadioButtonId == -1) {
                    Toast.makeText(context, "Veuillez répondre à la question ${i + 1}.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            for ((i, rg) in radioGroups.withIndex()) {
                val selectedId = rg.checkedRadioButtonId
                val rb = rg.findViewById<RadioButton>(selectedId)
                val score = rb.tag as Int
                SessionManager.perceptionApres["q${i + 1}"] = score
            }

            saveToFirebase()
        }
    }

    private fun saveToFirebase() {
        btnFinish.isEnabled = false
        val db = FirebaseFirestore.getInstance()
        
        val scoreTotal = SessionManager.comprehensionHistoire.sum()
        
        val data = hashMapOf(
            "idSession" to SessionManager.idSession,
            "age" to SessionManager.age,
            "knownStory" to SessionManager.knownStory,
            "condition" to SessionManager.condition,
            "scoreTotal" to scoreTotal,
            "perception_avant" to SessionManager.perceptionAvant,
            "perception_apres" to SessionManager.perceptionApres
        )

        // Ajout de r1, r2, r3... à la racine pour conserver la compatibilité avec l'existant
        SessionManager.comprehensionHistoire.forEachIndexed { index, value ->
            data["r${index + 1}"] = value
        }

        db.collection("Resultats_Experiences").document(SessionManager.idSession)
            .set(data)
            .addOnSuccessListener {
                Toast.makeText(context, "Données enregistrées avec succès !", Toast.LENGTH_LONG).show()
                findNavController().popBackStack(R.id.chooseFragment, false)
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Erreur: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
                btnFinish.isEnabled = true
            }
    }
}
