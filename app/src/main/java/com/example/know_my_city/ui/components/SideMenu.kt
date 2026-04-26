package com.example.know_my_city.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhotoLibrary
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material.icons.filled.Web
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SideMenu(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onOptionClick: () -> Unit = {}
) {
    val backgroundDarkBlue = androidx.compose.ui.graphics.Color(0xFF1A237E)
    val fontWhite = androidx.compose.ui.graphics.Color.White
    Surface( //Surface: contenedor con bordes y sombra
        modifier = modifier
            .fillMaxWidth(0.45f)
            .fillMaxHeight(),
        color = backgroundDarkBlue
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 4.dp, end = 8.dp, top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Menu",
                style = MaterialTheme.typography.titleMedium,
                color = fontWhite,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            )

            val btnStyle = ButtonDefaults.buttonColors(
                containerColor = androidx.compose.ui.graphics.Color.Transparent,
                contentColor = fontWhite
            )

            //Opcions del menú Perfil
            Button(
                onClick = {
                    navController.navigate("perfil") { launchSingleTop = true }
                    onOptionClick()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = btnStyle
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil"
                )
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Text(
                    text = "Perfil",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            //Opcions del menú fotos
            Button(
                onClick = {
                    navController.navigate("fotos") { launchSingleTop = true }
                    onOptionClick()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = btnStyle
            ) {
                Icon(
                    imageVector = Icons.Default.PhotoLibrary,
                    contentDescription = "Fotos"
                )
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Text(
                    text = "Fotos",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            // Opciones del menú videos
            Button(
                onClick = {
                    navController.navigate("videos") { launchSingleTop = true }
                    onOptionClick()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = btnStyle
            ) {
                Icon(
                    imageVector = Icons.Default.VideoLibrary,
                    contentDescription = "Videos"
                )
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Text(
                    text = "Videos",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            // Opciones del menú Navegador
            Button(
                onClick = {
                    navController.navigate("navegador") { launchSingleTop = true }
                    onOptionClick()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = btnStyle
            ) {
                Icon(
                    imageVector = Icons.Default.Web,
                    contentDescription = "Navegador"
                )
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Text(
                    text = "Navegador",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            // Opciones del menú Otros
            Button(
                onClick = {
                    navController.navigate("otros") { launchSingleTop = true }
                    onOptionClick()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = btnStyle
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Help,
                    contentDescription = "Otros"
                )
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Text(
                    text = "Otros",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
