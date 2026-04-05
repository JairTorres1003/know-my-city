package com.example.know_my_city

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.know_my_city.ui.MainScreen
import com.example.know_my_city.ui.theme.KnowmycityTheme

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.know_my_city.ui.components.ProfileScreen

import com.example.know_my_city.ui.components.fotosCiudad



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KnowmycityTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main") {
                            MainScreen(
                                modifier = Modifier.fillMaxSize(),
                                contentPadding = innerPadding,
                                navController = navController
                            )
                        }
                        composable("perfil") {
                            ProfileScreen()
                        }

                        composable("fotos"){
                            fotosCiudad()
                        }
                    }
                }
            }
        }
    }
}