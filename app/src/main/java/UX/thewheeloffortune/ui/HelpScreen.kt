package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import UX.thewheeloffortune.ui.theme.TheWheelOfFortuneTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Contains rules of the game
 */
@Composable
fun HelpScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(16.dp)
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

        // TODO: ADD RULES!
        item {

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