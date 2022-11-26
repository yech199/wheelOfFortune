package UX.thewheeloffortune.ui

import UX.thewheeloffortune.R
import UX.thewheeloffortune.data.Categories
import UX.thewheeloffortune.data.Options.categories
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 * stringRessource can only be used in composables. Therefore the title is not a string
 */
enum class Screen(@StringRes val title: Int) {
    MainMenu(title = R.string.main_menu),
    Category(title = R.string.categories),
    HighScores(title = R.string.high_scores),
    Help(title = R.string.help),
    Game(title = R.string.app_name),
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    // The current back stack entry as a state
    val backStackEntry by navController.currentBackStackEntryAsState()
    // The name of the current screen
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.MainMenu.name
    )

    Scaffold(
        topBar = {
            TopBar(
                currentScreen = currentScreen,
                canNavBack = navController.previousBackStackEntry != null,
                navBack = { navController.navigateUp() })
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Screen.MainMenu.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = Screen.MainMenu.name) {
                MainMenuScreen(
                    onPlayClicked = { navController.navigate(Screen.Category.name) },
                    onHighScoresClicked = { navController.navigate(Screen.HighScores.name) },
                    onHelpClicked = { navController.navigate(Screen.Help.name) }
                )
            }
            composable(route = Screen.Category.name) {
                CategoryScreen(
                    categories = categories,
                    onButtonClicked = {
                        viewModel.setWordToBeGuessed()
                        navController.navigate(Screen.Game.name)
                    },
                    onChangeSelection = { viewModel.setCategory(Categories.getCategoryEnum(it)) }
                )
            }
            composable(route = Screen.HighScores.name) {
//                HighScoreScreen(
//                    onButtonClicked = { navController.navigate(Screen.Game.name) }
//                )
            }
            composable(route = Screen.Help.name) {
                HelpScreen()
            }
            composable(route = Screen.Game.name) {
                GameScreen(
                    NoLives = uiState.NoLives,
                    score = uiState.score,
                    category = uiState.category,
                    wordToGuess = uiState.GameScreenLetters,
                    isWheelSpun = uiState.isWheelSpun,
                    onSpinWheel = { viewModel.spinWheel() },
                    currentPointChance = uiState.currentPointChance,
                    buttonOptions = uiState.gameButtons,
                    onGuess = { viewModel.checkGuess(it) },
                    isGameEnded = uiState.isGameOver,
                    onPlayAgain = {
                        navController.popBackStack(Screen.Category.name, inclusive = false)
                        viewModel.resetGame()
                    },
                    onExit = {
                        navController.popBackStack(Screen.MainMenu.name, inclusive = false)
                        viewModel.resetGame()
                    }
                
                )
            }
        }
    }
}

/**
 * The top bar showing which screen the current screen is
 */
@Composable
fun TopBar(
    currentScreen: Screen,
    canNavBack: Boolean,
    navBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(text = stringResource(id = currentScreen.title)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavBack) {
                IconButton(onClick = navBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back_button
                        )
                    )
                }
            }
        }

    )
}