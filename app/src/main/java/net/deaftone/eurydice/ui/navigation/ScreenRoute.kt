package net.deaftone.eurydice.ui.navigation

import net.deaftone.eurydice.R

object NavGraph {
    const val BOTTOM_BAR_GRAPH = "bottom_bar_graph"
}

sealed class BottomBarScreen(
    val route: String,
    val title: Int,
    val icon: Int
) {

    object Albums : BottomBarScreen(
        route = "albums",
        title = R.string.app_name,
        icon = R.drawable.ic_home

    )

    object Artists : BottomBarScreen(
        route = "artists",
        title = R.string.app_name,
        icon = R.drawable.ic_home
    )
}

sealed class MainScreenRoutes(internal open val route: String) {
    object MediaDetail :
        MainScreenRoutes(
            route = "mediaDetail" +
                    "/{$ARG_MEDIA_TYPE}" +
                    "/{$ARG_MEDIA_ID}" +
                    "/{$ARG_MEDIA_NAME}" +
                    "/{$ARG_MEDIA_POSTER}"
        ) {
        fun withArgs(type: String, id: String, name: String, poster: String): String = route
            .replace("{$ARG_MEDIA_TYPE}", type)
            .replace("{$ARG_MEDIA_ID}", id)
            .replace("{$ARG_MEDIA_NAME}", name)
            .replace("{$ARG_MEDIA_POSTER}", poster)
    }

    companion object {
        const val ARG_MEDIA_TYPE: String = "type"
        const val ARG_MEDIA_ID: String = "id"
        const val ARG_MEDIA_NAME: String = "name"
        const val ARG_MEDIA_POSTER: String = "poster"
    }
}
