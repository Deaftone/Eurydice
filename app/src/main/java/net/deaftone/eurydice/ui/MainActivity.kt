package net.deaftone.eurydice.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import dagger.hilt.android.AndroidEntryPoint
import net.deaftone.eurydice.R
import net.deaftone.eurydice.ui.navigation.BottomBar
import net.deaftone.eurydice.ui.navigation.MainScreenNavGraph
import net.deaftone.core.MainScreenRoutes
import net.deaftone.eurydice.theme.EurydiceTheme
import net.deaftone.eurydice.ui.widget.TopAppBar


@OptIn(ExperimentalAnimationApi::class)
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
                    NavScreen(navController = rememberNavController())
                }
            }
        }
    }
}

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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun NavScreen(navController: NavHostController = rememberNavController()) {
    val hidden = listOf(
        MainScreenRoutes.AlbumInfo
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topBarDestination = hidden.any { it.route != currentDestination?.route }

    Scaffold(topBar = {
        AnimatedVisibility(
            visible = topBarDestination,
            enter = slideInVertically(animationSpec = spring(visibilityThreshold = IntOffset.Zero)),
            exit = slideOutVertically(animationSpec = spring(visibilityThreshold = IntOffset.Zero))
        ) {
            TopAppBar(stringResource(id = R.string.app_name))
        }
    }, bottomBar = { BottomBar(navController = navController) }) { padding ->
        MainScreenNavGraph(
            navController = navController,
            modifier = Modifier.padding(padding)
        )
    }
}
