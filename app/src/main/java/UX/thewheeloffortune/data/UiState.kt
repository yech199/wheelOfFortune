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
    val GameScreenLetters: String = "",
    val isWheelSpun: Boolean = false,
    val currentPointChance: Int = -1,
    val gameButtons: List<Pair<Char, Boolean>> = Options.alphabet,

    val isGuessValid: Boolean = false,
    val isGameOver: Boolean = false
)
