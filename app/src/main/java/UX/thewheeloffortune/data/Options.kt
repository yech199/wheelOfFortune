package UX.thewheeloffortune.data

import UX.thewheeloffortune.R
import androidx.annotation.StringRes


enum class Categories(@StringRes val categoryName: Int) {
    UNDEFINED(-1),
    COUNTRY(R.string.countries),
    MOVIE_TITLE(R.string.movie_titles),
    FOOD(R.string.food),
    ANIMAL(R.string.animals),
    ADJECTIVES(R.string.adjectives)
}

object Options {
    val categories = listOf(
        Categories.COUNTRY.categoryName,
        Categories.MOVIE_TITLE.categoryName,
        Categories.FOOD.categoryName,
        Categories.ANIMAL.categoryName,
        Categories.ADJECTIVES.categoryName
    )
    )
}