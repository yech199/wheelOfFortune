package UX.thewheeloffortune.data

/**
 * All states of the UI.
 */
data class UiState(
    val category: String = "",
    val currentWordToGuess: String = "",
    val NoLives: Int = 5,
    val score: Int = 0,
    val isGuessValid: Boolean = false,
    val isGameOver: Boolean = false
)
