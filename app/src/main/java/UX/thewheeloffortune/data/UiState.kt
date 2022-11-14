package UX.thewheeloffortune.data

/**
 * All states of the UI.
 */
data class UiState(
    val category: Categories = Categories.UNDEFINED,
//    val isDarkMode: Boolean = false,
    val NoLives: Int = 5,
    val score: Int = 0,

    val currentWordToGuess: String = "",
    val isGuessValid: Boolean = false,
    val isGameOver: Boolean = false
)
