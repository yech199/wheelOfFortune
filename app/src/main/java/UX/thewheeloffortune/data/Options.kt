package UX.thewheeloffortune.data

import UX.thewheeloffortune.R
import androidx.annotation.StringRes

enum class Categories(@StringRes val categoryName: Int) {
    UNDEFINED(-1),
    COUNTRY(R.string.countries),
    MOVIE_TITLE(R.string.movie_titles),
    FOOD(R.string.food),
    ANIMAL(R.string.animals),
    ADJECTIVES(R.string.adjectives);

    companion object {
        fun getCategoryEnum(categoryName: Int): Categories {
            values().forEach { item ->
                if (item.categoryName == categoryName)
                    return item
            }
            // Should never happen
            return UNDEFINED
        }

    }
}

object Options {
    val categories = listOf(
        Categories.COUNTRY.categoryName,
        Categories.MOVIE_TITLE.categoryName,
        Categories.FOOD.categoryName,
        Categories.ANIMAL.categoryName,
        Categories.ADJECTIVES.categoryName
    )

    val alphabet = listOf(
        Pair('A', true),
        Pair('B', true),
        Pair('C', true),
        Pair('D', true),
        Pair('E', true),
        Pair('F', true),
        Pair('G', true),
        Pair('H', true),
        Pair('I', true),
        Pair('J', true),
        Pair('K', true),
        Pair('L', true),
        Pair('M', true),
        Pair('N', true),
        Pair('O', true),
        Pair('P', true),
        Pair('Q', true),
        Pair('R', true),
        Pair('S', true),
        Pair('T', true),
        Pair('U', true),
        Pair('V', true),
        Pair('W', true),
        Pair('X', true),
        Pair('Y', true),
        Pair('Z', true)
    )
}