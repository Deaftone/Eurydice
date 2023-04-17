package net.deaftone.eurydice.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

@Composable
fun TestScreen1(
    onItemClick: () -> Unit,
    name: String
) {
    Text(text = "Hello $name!")
}
