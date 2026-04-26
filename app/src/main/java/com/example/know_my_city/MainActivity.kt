package com.example.know_my_city

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.know_my_city.ui.components.DynamicContent
import com.example.know_my_city.ui.components.NavegadorScreen
import com.example.know_my_city.ui.components.OtrosScreen
import com.example.know_my_city.ui.components.ProfileScreen
import com.example.know_my_city.ui.components.SideMenu
import com.example.know_my_city.ui.components.SplashScreen
import com.example.know_my_city.ui.components.VideosScreen
import com.example.know_my_city.ui.components.fotosCiudad
import com.example.know_my_city.ui.theme.KnowmycityTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KnowmycityTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                ModalNavigationDrawer(
                    drawerState = drawerState,
                    drawerContent = {
                        SideMenu(
                            navController = navController,
                            onOptionClick = { scope.launch { drawerState.close() } }
                        )
                    }
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        NavHost(
                            navController = navController,
                            startDestination = "splash",
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable("splash") {
                                SplashScreen(navController = navController)
                            }
                            composable("main") {
                                DynamicContent(modifier = Modifier.fillMaxSize())
                            }
                            composable("perfil") { ProfileScreen() }
                            composable("fotos") { fotosCiudad() }
                            composable("videos") { VideosScreen() }
                            composable("navegador") { NavegadorScreen() }
                            composable("otros") { OtrosScreen() }
                        }

                        if (currentRoute != "splash") {
                            IconButton(
                                onClick = { scope.launch { drawerState.open() } },
                                modifier = Modifier
                                    .align(Alignment.TopStart)
                                    .statusBarsPadding()
                                    .padding(start = 8.dp, top = 8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Abrir menú",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
