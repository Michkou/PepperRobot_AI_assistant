package com.example.pepperapp.data

object SessionManager {
    var idSession: String = ""
    var condition: String = "" // e.g. "EL1", "NL1", "EO2", "NO2"
    var age: Int = 0
    var knownStory: Boolean = false
    
    // Maps "q1" -> 3 (1=Pas du tout, 2=Un peu, 3=Oui, 4=Beaucoup)
    val perceptionAvant = mutableMapOf<String, Int>()
    
    // List of 1 (correct) or 0 (incorrect)
    val comprehensionHistoire = mutableListOf<Int>()
    
    // Maps "q1" -> 4
    val perceptionApres = mutableMapOf<String, Int>()

    fun reset() {
        idSession = ""
        condition = ""
        age = 0
        knownStory = false
        perceptionAvant.clear()
        comprehensionHistoire.clear()
        perceptionApres.clear()
    }
}
