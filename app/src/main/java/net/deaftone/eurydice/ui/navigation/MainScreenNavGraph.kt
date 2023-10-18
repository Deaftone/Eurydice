package net.deaftone.eurydice.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.deaftone.eurydice.ui.TestScreen1
import net.deaftone.eurydice.ui.album.AlbumInfoScreen
import net.deaftone.eurydice.ui.album.AlbumListScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreenNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = NavGraph.BOTTOM_BAR_GRAPH,
        startDestination = BottomBarScreen.AlbumList.route,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
        exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
        popEnterTransition = {
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
        },
        popExitTransition = {
            slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
        }

    ) {
        composable(route = BottomBarScreen.Artists.route) {
            TestScreen1(onItemClick = {
                navController.navigate(BottomBarScreen.Artists.route)
            }, name = "test")
        }
        composable(route = BottomBarScreen.AlbumList.route) {
            AlbumListScreen(onItemClick = {
                navController.navigate(BottomBarScreen.AlbumList.route)
            }, onNavigationUp = {
                navController.popBackStack()
            },
            navController = navController)
        }

        composable(route = MainScreenRoutes.AlbumInfo.route) { navBackStackEntry ->
            AlbumInfoScreen(onNavigationUp = {
                navController.popBackStack()
            })
        }

       /* composable(route = MainScreenRoutes.SettingsScreen.route) {
            SettingsScreen(onNavigationUp = {
                navController.popBackStack()
            })
        }*/
    }
}