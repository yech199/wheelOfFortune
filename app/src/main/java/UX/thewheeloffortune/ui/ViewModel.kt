package UX.thewheeloffortune.ui

import UX.thewheeloffortune.data.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import kotlin.random.Random

/**
 * Holds the logic and calculations of the app
 */
class ViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private var usedLetters: MutableSet<Char> = mutableSetOf()
    private var usedWords: MutableSet<String> = mutableSetOf()

    init {
        usedWords.clear()
        resetGame()
    }

    fun resetGame() {
        usedLetters.clear()
        val cat = uiState.value.category
        _uiState.value = UiState(category = cat)
    }

    /**
     * Contains logic for spinning the wheel.
     */
    fun spinWheel() {
        val point = points.random()

        // Makes sure bankrupt does not happen twice in a row
        if (point == -1 && uiState.value.currentPointChance == -1)
            return spinWheel()

        _uiState.update { currentState ->
            currentState.copy(
                isWheelSpun = true,
                currentPointChance = point
            )
        }

        // Bankrupt
        if (point == -1) {
            _uiState.update { currentState ->
                currentState.copy(
                    score = 0,
                )
            }
        }
    }

    /**
     * Check if the guess of the player if correct and update values as needed
     */
    fun checkGuess(guess: Char) {
        // Check if guess is part of wordToGuess -> Update hiddenWord
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

        // If guess is wrong -> Lose one life
        if (!hiddenWord.contains(guess)) {
            _uiState.update { currentState ->
                currentState.copy(
                    NoLives = uiState.value.NoLives - 1,
                    isWheelSpun = false,
                )
            }
        }

        // Always update these when guessing
        _uiState.update { currentState ->
            currentState.copy(
                score = uiState.value.score +
                        (uiState.value.currentPointChance * letterOccurrence),
                isWheelSpun = false,
                GameScreenLetters = hiddenWord.concatToString(),
                gameButtons = disableClickedButton(guess)
            )
        }

        if (!uiState.value.GameScreenLetters.contains(' ')
            || uiState.value.NoLives < 1
        ) {
            _uiState.update { currentState ->
                currentState.copy(
                    isGameOver = true
                )
            }
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
        val word = getRandomWord(uiState.value.category)
        _uiState.update { currentState ->
            currentState.copy(
                currentWordToGuess = word,
                score = 0,
            )
        }
    }

    //---------------------------------------------------------------------
    // Private functions
    //---------------------------------------------------------------------

    /**
     * Get a random word from a category chosen by the player
     */
    private fun getRandomWord(
        category: Categories
    ): String {
        val seed = Random(Date().time)
        val wordToGuess = when (category) {
            Categories.COUNTRY -> countries.random(seed)
            Categories.MOVIE_TITLE -> movies.random(seed)
            Categories.FOOD -> food.random(seed)
            Categories.ANIMAL -> animals.random(seed)
            Categories.ADJECTIVES -> adjectives.random(seed)
            Categories.UNDEFINED -> getRandomWord(Categories.COUNTRY)
        }

        if (usedWords.size >= 20)
            usedWords.clear()

        if (usedWords.contains(wordToGuess)) {
            return getRandomWord(category)
        }

        usedWords.add(wordToGuess)

        _uiState.update { currentState ->
            currentState.copy(
                GameScreenLetters = "".padStart(wordToGuess.length, ' ')
            )
        }
        return wordToGuess.uppercase()
    }

    /**
     * Disables a button when clicked. This is used when the player
     * tries to guess a letter
     */
    private fun disableClickedButton(guess: Char): List<Pair<Char, Boolean>> {
        // Disable button
        val letterButtons: MutableList<Pair<Char, Boolean>> = ArrayList()
        uiState.value.gameButtons.forEach { button ->
            val tmp: Pair<Char, Boolean>
            if (button.first == guess) {
                tmp = button.copy(second = false)
                letterButtons.add(tmp)
            } else {
                tmp = button.copy()
                letterButtons.add(tmp)
            }
        }
        return letterButtons
    }
}