package com.example.pepperapp.data

enum class Emotion {
    NEUTRAL,
    SAD,
    HAPPY
}

data class StoryLine(
    val text: String,
    val emotion: Emotion
)

