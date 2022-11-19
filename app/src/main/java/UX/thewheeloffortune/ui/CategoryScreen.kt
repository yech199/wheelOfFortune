package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryScreen(
    modifier: Modifier = Modifier,
    categories: List<Int>,
    onChangeSelection: (Int) -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {
    var selectedCategory by rememberSaveable { mutableStateOf(-1) }
    Column(
       modifier = modifier.padding(16.dp)
    ) {
        categories.forEach{ item ->
            Row(
                modifier = Modifier.selectable(
                    selected = selectedCategory == item,
                    onClick = {
                        selectedCategory = item
                        onChangeSelection(item)
                    }
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedCategory == item,
                    onClick = {
                        selectedCategory = item
                        onChangeSelection(item)
                    }
                )
                Text(text = stringResource(id = item))
            }
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedCategory != -1,
            onClick = onButtonClicked
        ) {
            Text(text = stringResource(id = R.string.play_category))
        }
    }
}

@Preview
@Composable
fun CategoryScreenPreview() {
//    CategoryScreen(categories = listOf("Option 1", "Option 2", "Option 3", "Option 4"))
}