package net.deaftone.eurydice.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import dagger.hilt.android.AndroidEntryPoint
import net.deaftone.album.ui.albumList.AlbumListScreen
import net.deaftone.data.MainScreenRoutes
import net.deaftone.eurydice.R
import net.deaftone.eurydice.ui.navigation.BottomBar
import net.deaftone.eurydice.ui.navigation.MainScreenNavGraph
import net.deaftone.eurydice.theme.EurydiceTheme
import net.deaftone.eurydice.ui.widget.TopAppBar


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EurydiceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigator(AlbumListScreen())
                }
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    EurydiceTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavScreen()
        }
    }
}
*/

@Composable
fun NavScreen(navController: NavHostController = rememberNavController()) {
    val hidden = listOf(
        MainScreenRoutes.AlbumInfo
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topBarDestination = hidden.any { it.route != currentDestination?.route }

    Scaffold(topBar = {
            TopAppBar(stringResource(id = R.string.app_name))
    }, bottomBar = { BottomBar(navController = navController) }) { padding ->
        MainScreenNavGraph(
            navController = navController,
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
private fun BottomNavigation() {
    BottomNavigation(
    ) {
        TabNavigationItem(HomeTab)
        TabNavigationItem(AddTab)
        TabNavigationItem(ProfileTab)
    }
}
@Composable
private fun RowScope.TabNavigationItem(
    tab: Tab,
) {
    val tabNavigator = LocalTabNavigator.current
    BottomNavigationItem(
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title
            )
        },
        label = {
            Text(
                text = tab.options.title,
                style = AppTextStyle.menuLabel
            )
        },
        selectedContentColor = MaterialTheme.colors.primary,
        unselectedContentColor = MaterialTheme.colors.secondary
    )
}