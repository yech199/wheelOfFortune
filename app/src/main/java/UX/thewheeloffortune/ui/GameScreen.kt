package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
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

@Preview
@Composable
fun GameScreenPreview() {
    GameScreen(NoLives = 5, score = 1000)
}















