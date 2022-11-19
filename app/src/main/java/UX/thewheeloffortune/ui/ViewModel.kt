package UX.thewheeloffortune.ui

import UX.thewheeloffortune.data.*
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
        _uiState.value = UiState(category = Categories.UNDEFINED)
    }

    //---------------------------------------------------------------------
    // SETTERS
    //---------------------------------------------------------------------
    fun setCategory(category: Categories) {
        _uiState.update { currentState ->
            currentState.copy(
                category = category,
            )
        }
    }

    fun setWordToBeGuessed() {
        _uiState.update { currentState ->
            currentState.copy(
                currentWordToGuess = getRandomWord(uiState.value.category),
            )
        }
    }

    //---------------------------------------------------------------------
    // CALCULATE AND GET
    //---------------------------------------------------------------------
    private fun getRandomWord(category: Categories): String {
        val wordToGuess = when (category) {
            Categories.COUNTRY -> countries.random()
            Categories.MOVIE_TITLE -> movies.random()
            Categories.FOOD -> food.random()
            Categories.ANIMAL -> animals.random()
            Categories.ADJECTIVES -> adjectives.random()
            Categories.UNDEFINED -> getRandomWord(Categories.COUNTRY)
        }

//        if (usedWords.contains(wordToGuess)) {
//            return getRandomWord(category)
//        }

        usedWords.add(wordToGuess)

        _uiState.update { currentState ->
            currentState.copy(
                GameScreenLetters = "".padStart(wordToGuess.length, ' ')
            )
        }
        return wordToGuess.uppercase()
    }
}