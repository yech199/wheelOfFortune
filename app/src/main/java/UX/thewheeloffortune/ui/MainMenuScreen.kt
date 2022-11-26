package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainMenuScreen(
    modifier: Modifier = Modifier,
    onPlayClicked: () -> Unit,
    onHighScoresClicked: () -> Unit,
    onHelpClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            // source: https://www.flaticon.com/free-icon/wheel-of-fortune_2006249
            painter = painterResource(id = R.drawable.wheel),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = onPlayClicked,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 10.dp)
        ) {
            Text(
                text = stringResource(id = R.string.play),
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        OutlinedButton(
            onClick = onHighScoresClicked
        ) {
            Text(
                text = stringResource(id = R.string.high_scores),
                fontSize = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(50.dp))
        OutlinedButton(
            onClick = onHelpClicked
        ) {
            Text(
                text = stringResource(id = R.string.help),
                fontSize = 22.sp
            )
        }
    }
}

@Preview
@Composable
fun MainMenuScreenPreview() {
    MainMenuScreen(
        onPlayClicked = { },
        onHighScoresClicked = { },
        onHelpClicked = { }
    )
}