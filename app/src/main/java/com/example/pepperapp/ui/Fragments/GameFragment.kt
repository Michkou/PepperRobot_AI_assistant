package com.example.pepperapp.ui.Fragments

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pepperapp.R
import com.example.pepperapp.data.GameDatabase
import com.example.pepperapp.data.GameResponse
import kotlinx.coroutines.launch
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.`object`.locale.Language
import com.aldebaran.qi.sdk.`object`.locale.Locale
import com.aldebaran.qi.sdk.`object`.locale.Region
import com.example.pepperapp.ui.MainActivity

class GameFragment : Fragment() {

    private lateinit var spinnerAge: Spinner
    private lateinit var radioGroupKnown: RadioGroup
    private lateinit var questionText: TextView
    private lateinit var textKnownQuestion: TextView
    private lateinit var optionLayouts: List<LinearLayout>
    private lateinit var optionIcons: List<ImageView>
    private lateinit var optionTexts: List<TextView>
    
    // New layouts and buttons
    private lateinit var layoutInfo: LinearLayout
    private lateinit var layoutQuestions: LinearLayout
    private lateinit var buttonBackInfo: Button
    private lateinit var buttonNextInfo: Button
    private lateinit var buttonBackQuestions: Button

    private var currentIndex = 0
    private var score = 0
    private val answers = mutableListOf<Int>()

    private var questions: List<com.example.pepperapp.data.Question> = emptyList()
    private var currentStoryTitle: String = ""

    private var sayFuture: com.aldebaran.qi.Future<Void>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve storyId from arguments
        val storyId = arguments?.getInt("storyId") ?: 1
        val story = com.example.pepperapp.data.StoryRepository.getStoryById(storyId)
        
        // Load questions from the story, or empty if story not found
        questions = story?.questions ?: emptyList()
        currentStoryTitle = story?.title ?: "Monstre des Couleurs"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        spinnerAge = view.findViewById(R.id.spinnerAge)
        
        // Setup Spinner with ages 3 to 9
        val ages = (3..9).toList().map { it.toString() }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, ages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAge.adapter = adapter
        radioGroupKnown = view.findViewById(R.id.radioGroupKnown)
        radioGroupKnown = view.findViewById(R.id.radioGroupKnown)
        questionText = view.findViewById(R.id.questionText)
        textKnownQuestion = view.findViewById(R.id.textKnownQuestion)
        
        layoutInfo = view.findViewById(R.id.layoutInfo)
        layoutQuestions = view.findViewById(R.id.layoutQuestions)
        buttonBackInfo = view.findViewById(R.id.buttonBackInfo)
        buttonNextInfo = view.findViewById(R.id.buttonNextInfo)
        buttonBackQuestions = view.findViewById(R.id.buttonBackQuestions)

        textKnownQuestion.text = "Connais-tu l'histoire de $currentStoryTitle ?"

        optionLayouts = listOf(
            view.findViewById(R.id.optionLayoutA),
            view.findViewById(R.id.optionLayoutB),
            view.findViewById(R.id.optionLayoutC),
            view.findViewById(R.id.optionLayoutD),
            view.findViewById(R.id.optionLayoutE),
            view.findViewById(R.id.optionLayoutF)
        )

        optionIcons = listOf(
            view.findViewById(R.id.optionIconA),
            view.findViewById(R.id.optionIconB),
            view.findViewById(R.id.optionIconC),
            view.findViewById(R.id.optionIconD),
            view.findViewById(R.id.optionIconE),
            view.findViewById(R.id.optionIconF)
        )

        optionTexts = listOf(
            view.findViewById(R.id.optionTextA),
            view.findViewById(R.id.optionTextB),
            view.findViewById(R.id.optionTextC),
            view.findViewById(R.id.optionTextD),
            view.findViewById(R.id.optionTextE),
            view.findViewById(R.id.optionTextF)
        )

        optionLayouts.forEachIndexed { idx, layout ->
            layout.setOnClickListener { onOptionSelected(idx) }
        }

        buttonBackInfo.setOnClickListener { 
            findNavController().popBackStack() 
        }
        
        buttonNextInfo.setOnClickListener {
            // Hide info, show questions
            layoutInfo.visibility = View.GONE
            layoutQuestions.visibility = View.VISIBLE
            // Start the game logic
            showQuestion()
        }
        
        buttonBackQuestions.setOnClickListener {
            // Cancel speech and go back to Info
            sayFuture?.requestCancellation()
            layoutQuestions.visibility = View.GONE
            layoutInfo.visibility = View.VISIBLE
            
            // Note: score and currentIndex are kept as-is if they just want to peek
            // To reset, we would need to reset states here. Assuming they just go back to edit info.
        }

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
                
                // Set the text and icon
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
        // Cancel any ongoing speech
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

        if (selected == questions[currentIndex].correctIndex) score++
        answers.add(selected)
        currentIndex++

        if (currentIndex < questions.size) {
            showQuestion()
        } else {
            finishGame()
        }
    }

    private fun finishGame() {
        optionLayouts.forEach { it.isEnabled = false }

        // Name is removed, age comes from spinner
        val name = "Enfant"
        val age = spinnerAge.selectedItem?.toString()?.toIntOrNull() ?: 0
        val isKnown = if (radioGroupKnown.checkedRadioButtonId == R.id.radioKnownYes) 1 else 0

        val correctness = questions.mapIndexed { i, q ->
            if (answers.getOrNull(i) == q.correctIndex) 1 else 0
        }

        val scoreValue = correctness.sum()

        // --- Logique Firebase Firestore ---
        val storyId = arguments?.getInt("storyId") ?: 1
        // ID 1 et 3 = Emotion, ID 2 et 4 = Sans Emotion
        val isEmotion = storyId == 1 || storyId == 3
        // ID 1 et 2 = L, ID 3 et 4 = O
        val isFirstGroup = storyId == 1 || storyId == 2

        val xVal = if (isEmotion) "E" else "N"
        val yVal = if (isFirstGroup) "L" else "O"

        val dateFormat = java.text.SimpleDateFormat("mmssSSS", java.util.Locale.getDefault())
        val timeStr = dateFormat.format(java.util.Date())
        val idSession = timeStr + xVal + yVal

        val sessionData = com.example.pepperapp.data.SessionData(
            idSession = idSession,
            age = age,
            r1 = correctness.getOrNull(0) ?: 0,
            r2 = correctness.getOrNull(1) ?: 0,
            r3 = correctness.getOrNull(2) ?: 0,
            r4 = correctness.getOrNull(3) ?: 0,
            r5 = correctness.getOrNull(4) ?: 0,
            r6 = correctness.getOrNull(5) ?: 0,
            r7 = correctness.getOrNull(6) ?: 0,
            r8 = correctness.getOrNull(7) ?: 0,
            scoreTotal = scoreValue
        )

        try {
            val db = com.google.firebase.firestore.FirebaseFirestore.getInstance()
            db.collection("Resultats_Experiences").document(idSession).set(sessionData)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Sauvegarde locale (optionnel mais utile en backup)
        viewLifecycleOwner.lifecycleScope.launch {
            GameDatabase.getInstance(requireContext())
                .gameResponseDao()
                .insert(
                    GameResponse(
                        childName = name,
                        age = age,
                        isKnown = isKnown,
                        q1 = correctness.getOrNull(0) ?: 0,
                        q2 = correctness.getOrNull(1) ?: 0,
                        q3 = correctness.getOrNull(2) ?: 0,
                        q4 = correctness.getOrNull(3) ?: 0,
                        q5 = correctness.getOrNull(4) ?: 0,
                        q6 = correctness.getOrNull(5) ?: 0,
                        q7 = correctness.getOrNull(6) ?: 0,
                        q8 = correctness.getOrNull(7) ?: 0,
                        score = scoreValue
                    )
                )

            Toast.makeText(requireContext(), "Merci Enfant ! Score : $scoreValue/${questions.size}", Toast.LENGTH_LONG).show()
            speakConcurrently("Bravo ! Enfant Ton score est de $scoreValue sur ${questions.size}.")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sayFuture?.requestCancellation()
    }
}
