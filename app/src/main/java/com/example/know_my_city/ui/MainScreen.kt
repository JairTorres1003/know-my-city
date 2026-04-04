package com.example.know_my_city.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.know_my_city.ui.components.DynamicContent
import com.example.know_my_city.ui.components.SideMenu
import com.example.know_my_city.ui.theme.KnowmycityTheme

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues()
) {
    var isMenuOpen by rememberSaveable { mutableStateOf(false) }
    val overlayInteractionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .padding(contentPadding)
            .fillMaxSize()
    ) {
        // Main content area
        DynamicContent(
            onMenuToggle = { isMenuOpen = !isMenuOpen }
        )

        // Semi-transparent overlay when menu is open
        if (isMenuOpen) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(
                        indication = null,
                        interactionSource = overlayInteractionSource
                    ) {
                        isMenuOpen = false
                    }
            )
        }

        // Collapsible side menu - slides in from left
        AnimatedVisibility(
            visible = isMenuOpen,
            enter = slideInHorizontally(initialOffsetX = { -it }),
            exit = slideOutHorizontally(targetOffsetX = { -it })
        ) {
            SideMenu()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    KnowmycityTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            MainScreen(contentPadding = innerPadding)
        }
    }
}
