package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import UX.thewheeloffortune.data.Categories
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Screen where the game is displayed
 */
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    NoLives: Int,
    score: Int,
    category: Categories = Categories.COUNTRY,
    wordToGuess: String = "            ",
    isWheelSpun: Boolean,
    onSpinWheel: () -> Unit,
    currentPointChance: Int = 0,
    buttonOptions: List<Pair<Char, Boolean>>,
    onGuess: (Char) -> Unit,
    isGameEnded: Boolean = false,
    onPlayAgain: () -> Unit,
    onExit: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GameStatus(NoLives = NoLives, score = score)
        GameLayout(
            category = category,
            wordToGuess = wordToGuess
        )
        Interactionable(
            isWheelSpun = isWheelSpun,
            onSpinWheel = onSpinWheel,
            currentPointChance = currentPointChance,
            buttonOptions = buttonOptions,
            onGuess = onGuess
        )

        if (NoLives < 1) {
            GameLostDialog(
                onPlayAgain = onPlayAgain,
                onExit = onExit
            )
        } else if (isGameEnded) {
            GameWonDialog(
                NoLives = NoLives,
                score = score,
                onPlayAgain = onPlayAgain,
                onExit = onExit
            )
        }
    }
}

/**
 * Status bar displaying remaining lives and score
 */
@Composable
fun GameStatus(
    modifier: Modifier = Modifier,
    NoLives: Int,
    score: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        for (i in 1..NoLives) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color.Red
            )
        }

        for (i in 1..5 - NoLives) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = null,
            )
        }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = stringResource(id = R.string.score, score),
            fontSize = 18.sp
        )
    }
}

/**
 * The part of the screen that displays the chosen category and the hidden word
 */
@Composable
fun GameLayout(
    modifier: Modifier = Modifier,
    category: Categories,
    wordToGuess: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                color = Color(242, 237, 245),
                shape = RoundedCornerShape(4.dp)
            ),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = category.categoryName),
                fontSize = 25.sp
            )

            LazyVerticalGrid(
                modifier = modifier,
                columns = GridCells.Adaptive(33.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 16.dp,
                    end = 12.dp,
                    bottom = 16.dp
                )
            ) {
                items(wordToGuess.toList()) { letter ->
                    BoxWithConstraints(
                        modifier = modifier
                            .background(
                                Color(181, 179, 186),
                                RoundedCornerShape(4.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = letter.toString(),
                            fontSize = 30.sp
                        )
                    }
                }
            }
        }
    }
}

/**
 * Everything under the GameLayout
 * - Button for spinning wheel
 * - Buttons used for guessing
 * - Text showing how many points the guess will give per occurrence if correct
 */
@Composable
private fun Interactionable(
    modifier: Modifier = Modifier,
    isWheelSpun: Boolean,
    onSpinWheel: () -> Unit,
    currentPointChance: Int,
    buttonOptions: List<Pair<Char, Boolean>>,
    onGuess: (Char) -> Unit,
) {

    if (isWheelSpun) {
        if (currentPointChance != -1) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.cur_point, currentPointChance),
                fontSize = 30.sp
            )
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.game_msg),
                fontSize = 30.sp
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomCenter
            ) {
                LazyVerticalGrid(
                    modifier = modifier.fillMaxWidth(),
                    columns = GridCells.Adaptive(51.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    items(buttonOptions) { option ->
                        OptionButton(
                            option = option.first,
                            onClick = onGuess,
                            isEnabled = option.second
                        )
                    }
                }
            }
        } else {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.bankrupt),
                fontSize = 30.sp
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                onClick = onSpinWheel
            ) {
                Text(
                    text = stringResource(id = R.string.spin_again),
                    fontSize = 45.sp
                )
            }
        }
    } else {
        Spacer(modifier = modifier.height(10.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            onClick = onSpinWheel
        ) {
            Text(
                text = stringResource(id = R.string.spin_wheel),
                fontSize = 45.sp
            )
        }
    }
}

/**
 * Button used for guessing a letter
 */
@Composable
fun OptionButton(
    option: Char,
    onClick: (Char) -> Unit,
    isEnabled: Boolean
) {
    Button(
        onClick = {
            onClick(option)
        },
        shape = RoundedCornerShape(8.dp),
        enabled = isEnabled,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = option.toString(),
            fontSize = 30.sp
        )
    }
}

/**
 * Popup dialog when the game is lost.
 * - From here the player can quit the game or start over
 */
@Composable
fun GameLostDialog(
    modifier: Modifier = Modifier,
    onPlayAgain: () -> Unit,
    onExit: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {},
        title = { Text(text = stringResource(id = R.string.game_lost)) },
        text = { Text(text = stringResource(id = R.string.lose_msg)) },
        dismissButton = {
            TextButton(onClick = onExit) {
                Text(text = stringResource(id = R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(id = R.string.play_again))
            }
        }
    )
}

/**
 * Popup dialog when the game is won.
 * - From here the player can quit the game or start over
 */
@Composable
fun GameWonDialog(
    modifier: Modifier = Modifier,
    NoLives: Int,
    score: Int,
    onPlayAgain: () -> Unit,
    onExit: () -> Unit
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {},
        title = { Text(text = stringResource(id = R.string.congratz)) },
        text = {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.winner_score, score))
                Text(text = stringResource(id = R.string.winner_life, NoLives))
            }
        },
        dismissButton = {
            TextButton(onClick = onExit) {
                Text(text = stringResource(id = R.string.exit))
            }
        },
        confirmButton = {
            TextButton(onClick = onPlayAgain) {
                Text(text = stringResource(id = R.string.play_again))
            }
        }
    )
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(
        NoLives = 5,
        score = 1000,
        isWheelSpun = true,
        onSpinWheel = {},
        currentPointChance = 250,
        onGuess = {},
        buttonOptions = listOf(
            Pair('A', true),
            Pair('B', true),
            Pair('C', true),
            Pair('D', false),
            Pair('E', true),
            Pair('F', false),
            Pair('G', false),
            Pair('H', false),
            Pair('I', false),
            Pair('J', false),
            Pair('K', false),
            Pair('L', false),
            Pair('M', false),
            Pair('N', false),
        ),
        onPlayAgain = {},
        onExit = {}
    )
}