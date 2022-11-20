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

    private var usedLetters: MutableSet<Char> = mutableSetOf()
    private var usedWords: MutableSet<String> = mutableSetOf()


    init {
        resetGame()
    }

    fun resetGame() {
        usedLetters.clear()
        _uiState.value = UiState(category = Categories.UNDEFINED)
    }

    fun spinWheel() {
        val point = points.random()
        if (point == -1 && uiState.value.currentPointChance == -1)
            return spinWheel()
        _uiState.update { currentState ->
            currentState.copy(
                isWheelSpun = true,
                currentPointChance = point
            )
        }
        if (point == -1) {
            _uiState.update { currentState ->
                currentState.copy(
                    score = 0,
                )
            }
        }
    }

    fun checkGuess(guess: Char) {
        var letterOccurrence = 0
        usedLetters.add(guess)
        val wordToGuess = uiState.value.currentWordToGuess.toList()
        val hiddenWord = uiState.value.GameScreenLetters.toCharArray()
        for ((i, letter) in wordToGuess.withIndex()) {
            if (letter == guess) {
                hiddenWord[i] = letter
                letterOccurrence++
            }
        }
        println(hiddenWord.concatToString())


        if (!hiddenWord.contains(guess)) {
            _uiState.update { currentState ->
                currentState.copy(
                    NoLives = uiState.value.NoLives - 1,
                    isWheelSpun = false,
                )
            }

        }

        _uiState.update { currentState ->
            currentState.copy(
                score = uiState.value.score +
                        (uiState.value.currentPointChance * letterOccurrence),
                isWheelSpun = false,
                GameScreenLetters = hiddenWord.concatToString()
            )
        }
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
                score = 0,
            )
        }
    }

    //---------------------------------------------------------------------
    // Private functions
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