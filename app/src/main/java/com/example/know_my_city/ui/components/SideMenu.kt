package com.example.know_my_city.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer //importamos el espacio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon //importamos el icono
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
//importar libraria de material design
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.navigation.NavHostController

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material.icons.filled.PhotoLibrary

@Composable
fun SideMenu(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Surface( //Surface: contenedor con bordes y sombra
        modifier = modifier
            .fillMaxWidth(0.85f)
            .fillMaxHeight(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Menu",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )

            //Opcions del menú Perfil
            Button(
                onClick = { navController.navigate("perfil") },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,  // fondo azul/color del tema
                    contentColor = MaterialTheme.colorScheme.onPrimary   // ícono y texto en blanco
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil"
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Perfil",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }

            //Opcions del menú fotos
            Button(
                onClick = { navController.navigate("fotos") },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoLibrary,
                    contentDescription = "Fotos"
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Fotos",
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
