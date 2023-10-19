package net.deaftone.eurydice.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import net.deaftone.album.ui.albumInfo.AlbumInfoScreen
import net.deaftone.album.ui.albumList.AlbumListScreen
import net.deaftone.core.BottomBarScreen
import net.deaftone.eurydice.ui.TestScreen1


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreenNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        route = net.deaftone.core.NavGraph.BOTTOM_BAR_GRAPH,
        startDestination = net.deaftone.core.BottomBarScreen.AlbumList.route,
        enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right) },
        exitTransition = { slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left) },
        popEnterTransition = {
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
        },
        popExitTransition = {
            slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
        }

    ) {
        composable(route = net.deaftone.core.BottomBarScreen.Artists.route) {
            TestScreen1(onItemClick = {
                navController.navigate(net.deaftone.core.BottomBarScreen.Artists.route)
            }, name = "test")
        }
        composable(route = net.deaftone.core.BottomBarScreen.AlbumList.route) {
            AlbumListScreen(onItemClick = {
                navController.navigate(BottomBarScreen.AlbumList.route)
            }, onNavigationUp = {
                navController.popBackStack()
            },
                navController = navController
            )
        }

        composable(route = net.deaftone.core.MainScreenRoutes.AlbumInfo.route) { navBackStackEntry ->
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