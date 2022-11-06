package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainMenuScreen(
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.play))

        }
    }
}

@Preview
@Composable
fun MainMenuScreenPreview() {
    MainMenuScreen(
        onButtonClicked = {}
    )
}