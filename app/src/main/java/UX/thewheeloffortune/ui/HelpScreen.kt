package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import UX.thewheeloffortune.ui.theme.TheWheelOfFortuneTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Contains rules of the game (source: assignment description)
 */
@Composable
fun HelpScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.rules),
                    fontSize = 50.sp,
                )
            }
        }
        item {
            Text(text = "1. When the game starts, the player has to choose a category.")
        }
        item {
            Text(
                text = "2. When a category has been chosen, a word within this " +
                        "category is randomly chosen for the player to guess."
            )
        }
        item {
            Text(text = "3. The word the user needs to guess is displayed with the letters hidden.")
        }
        item {
            Text(
                text = "4. Before guessing a letter, the player needs to spin the wheel by " +
                        "tapping a button."
            )
        }
        item {
            Text(text = "5. After spinning the wheel a number of points or \"bankrupt\" is displayed.")
        }
        item {
            Text(
                text = "6. If \"bankrupt\" is displayed, the player loses all their points. The " +
                        "player then needs to spin the wheel again."
            )
        }
        item {
            Text(
                text = "7. If a value is being shown, the player needs to choose a letter not " +
                        "yet guessed."
            )
        }
        item {
            Text(
                text = "8. If the guessed letter is present in the word, the user's score is " +
                        "incremented by the value shown times the number of occurrences of the guessed letter. "
            )
        }
        item {
            Text(
                text = "9. If the guessed letter is present in the word, the occurrences of the letter" +
                        "are shown in the hidden word on the screen."
            )
        }
        item {
            Text(text = "10. If the guessed letter is not present, the player loses a life.")
        }
        item {
            Text(text = "11 The game continues until the game is either won, lost or exited.")
        }
        item {
            Text(
                text = "12. Winning condition: When all the letters have been guessed correctly, " +
                        "and the player still has at least one life left."
            )
        }
        item {
            Text(
                text = "13. Losing condition: When the player has no lived left and the word has" +
                        "not been guessed."
            )
        }
        item {
            Text(text = "14. A player starts with 5 lives")
            Spacer(modifier = modifier.height(50.dp))
        }
    }
}

@Preview
@Composable
fun HelpScreenPreview() {
    TheWheelOfFortuneTheme {
        HelpScreen()
    }
}