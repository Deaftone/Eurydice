package net.deaftone.eurydice.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import net.deaftone.eurydice.ui.TestScreen1
import net.deaftone.eurydice.ui.album.AlbumActivity
import net.deaftone.eurydice.ui.album.AlbumViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreenNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    AnimatedNavHost(
        modifier = modifier,
        navController = navController,
        route = NavGraph.BOTTOM_BAR_GRAPH,
        startDestination = BottomBarScreen.Albums.route,
        enterTransition = { slideIntoContainer(AnimatedContentScope.SlideDirection.Up) },
        exitTransition = { slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Up) },
        popEnterTransition = {
            slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Down)
        },
        popExitTransition = {
            slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Down)
        }

    ) {
        composable(route = BottomBarScreen.Albums.route) {
            TestScreen1(onItemClick = {
                navController.navigate(BottomBarScreen.Albums.route)
            }, name = "test")
        }
        composable(route = BottomBarScreen.Artists.route) {
            AlbumActivity(onItemClick = {
                navController.navigate(BottomBarScreen.Artists.route)
            }, onNavigationUp = {
                navController.popBackStack()
            })
        }
/*
        composable(route = MainScreenRoutes.MediaDetail.route) { navBackStackEntry ->
            val name = navBackStackEntry.arguments?.getString(MainScreenRoutes.ARG_MEDIA_NAME) ?: ""
            val posterUrl =
                navBackStackEntry.arguments?.getString(MainScreenRoutes.ARG_MEDIA_POSTER) ?: ""

            DetailsScreen(name, posterUrl, onNavigationUp = {
                navController.popBackStack()
            })
        }

        composable(route = MainScreenRoutes.SettingsScreen.route) {
            SettingsScreen(onNavigationUp = {
                navController.popBackStack()
            })
        }*/
    }
}