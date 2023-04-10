package net.deaftone.eurydice.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import net.deaftone.eurydice.R
import net.deaftone.eurydice.ui.navigation.BottomBar
import net.deaftone.eurydice.ui.navigation.BottomBarScreen
import net.deaftone.eurydice.ui.navigation.MainScreenNavGraph
import net.deaftone.eurydice.ui.navigation.MainScreenRoutes
import net.deaftone.eurydice.ui.widget.TopAppBar

@Composable
fun TestScreen1(onItemClick: () -> Unit,
                name: String) {
    Text(text = "Hello $name!")
}