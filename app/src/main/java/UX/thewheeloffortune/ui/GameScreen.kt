package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import UX.thewheeloffortune.data.Categories
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    NoLives: Int,
    score: Int,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        GameStatus(NoLives = NoLives, score = score)
        Interactionable(buttonOptions, onGuess, isWheelSpun)
    }
}

@Composable
private fun Interactionable(
    buttonOptions: List<Pair<Char, Boolean>>,
    onGuess: () -> Unit,
    isWheelSpun: Boolean
) {
    if (isWheelSpun) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(58.dp),
            ) {
                items(buttonOptions) { option ->
                    OptionButton(
                        option = option.first,
                        onClick = onGuess,
                        enabled = option.second
                    )
                }
            }
        }
    } else {

    }
}

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


@Composable
fun OptionButton(
    option: Char,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .padding(4.dp),
        enabled = enabled,
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = option.toString(),
            modifier = modifier
                .width(10.dp)
        )
    }
}

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(
        NoLives = 5,
        score = 1000,
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
        isWheelSpun = true
    )
}















