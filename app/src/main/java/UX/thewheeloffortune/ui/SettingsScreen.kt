package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean,
    onDarkModeChanged: (Boolean) -> Unit,
    textSize: TextUnit = 23.sp
) {
    Column(modifier = modifier
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = modifier
//                .height(25.dp)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = stringResource(id = R.string.dark_mode),
                fontSize = textSize
            )

            Switch(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End),
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = Color.LightGray,
                    checkedThumbColor = Color.Green
                ),
                checked = isDarkMode,
                onCheckedChange = onDarkModeChanged
            )
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen(isDarkMode = false, onDarkModeChanged = {})
}