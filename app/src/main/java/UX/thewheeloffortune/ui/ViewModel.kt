package UX.thewheeloffortune.ui

import UX.thewheeloffortune.data.UiState
import UX.thewheeloffortune.data.countries
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Holds the logic and calculations of the app
 */
class ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    var letterGuess by mutableStateOf("")
        private set
    private var usedLetters: MutableSet<Char> = mutableSetOf()

    private var usedWords: MutableSet<String> = mutableSetOf()
    private lateinit var wordToGuess: String



    init {
        resetGame()
    }

    fun resetGame() {
        usedLetters.clear()
        _uiState.value = UiState(currentWordToGuess = getRandomWord("countries")) // hardcoded
    }
    //---------------------------------------------------------------------
    // SETTERS
    //---------------------------------------------------------------------
    fun setCategory(category: String) {
        _uiState.update { currentState ->
            currentState.copy(
                category = category
            )
        }
    }



    //---------------------------------------------------------------------
    // CALCULATE AND GET
    //---------------------------------------------------------------------
    private fun getRandomWord(category: String): String {
        when(category) { // hardcoded
            "countries" -> wordToGuess = countries.random()
        }

        if (usedWords.contains(wordToGuess)) {
            return getRandomWord("countries") // hardcoded
        }
        else {
            usedWords.add(wordToGuess)
            return wordToGuess
        }
    }
}