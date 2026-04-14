package com.example.know_my_city.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen() {

    // Permite hacer scroll si el contenido es largo
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {

        // ── Banner superior con degradado ──
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A237E), // azul oscuro arriba
                            Color(0xFF0277BD)  // azul cielo abajo
                        )
                    )
                ),
            contentAlignment = Alignment.BottomCenter
        ) {
            // Texto decorativo de bienvenida en el banner
            Text(
                text = "Mi Perfil",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 24.dp)
            )

            // Círculo con iniciales, sobresale del banner hacia abajo
            Box(
                modifier = Modifier
                    .offset(y = 44.dp)
                    .size(88.dp)
                    .clip(CircleShape)
                    .background(Color.White), // borde blanco alrededor
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(
                            Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFF1565C0),
                                    Color(0xFF0277BD)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Iniciales del usuario
                    Text(
                        text = "JD",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Espacio para que el círculo no tape el nombre
        Spacer(modifier = Modifier.height(56.dp))

        // ── Nombre, correo y ciudad ──
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // Nombre del usuario
            Text(
                text = "Juan Díaz",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            // Correo electrónico
            Text(
                text = "juan.diaz@email.com",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Chip de ciudad
            Surface(
                shape = RoundedCornerShape(50.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Bogotá, Colombia",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Estadísticas del usuario ──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Tarjeta: Lugares visitados
            TarjetaEstadistica(
                emoji = "📍",
                cantidad = "12",
                etiqueta = "Lugares",
                modifier = Modifier.weight(1f)
            )

            // Tarjeta: Reseñas escritas
            TarjetaEstadistica(
                emoji = "✍️",
                cantidad = "5",
                etiqueta = "Reseñas",
                modifier = Modifier.weight(1f)
            )

            // Tarjeta: Favoritos guardados
            TarjetaEstadistica(
                emoji = "⭐",
                cantidad = "8",
                etiqueta = "Favoritos",
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Sección: Información personal ──
        Text(
            text = "Información personal",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Filas de información del perfil
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Fila: Nombre completo
            FilaInformacion(
                icono = Icons.Default.Person,
                etiqueta = "Nombre",
                valor = "Juan Díaz"
            )

            // Fila: Correo electrónico
            FilaInformacion(
                icono = Icons.Default.Email,
                etiqueta = "Correo",
                valor = "juan.diaz@email.com"
            )

            // Fila: Ciudad de residencia
            FilaInformacion(
                icono = Icons.Default.LocationOn,
                etiqueta = "Ciudad",
                valor = "Bogotá, Colombia"
            )

            // Fila: Nivel de explorador
            FilaInformacion(
                icono = Icons.Default.Star,
                etiqueta = "Nivel",
                valor = "Explorador Intermedio"
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // ── Botón principal: Editar perfil ──
        Button(
            onClick = { /* acción futura: abrir formulario de edición */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(50.dp),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1565C0)
            )
        ) {
            Text(
                text = "Editar perfil",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ── Botón secundario: Cerrar sesión ──
        OutlinedButton(
            onClick = { /* acción futura: cerrar sesión */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(50.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(
                text = "Cerrar sesión",
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

// ── Componente: tarjeta de estadística del usuario ──
// Muestra un emoji, un número y una etiqueta descriptiva
@Composable
fun TarjetaEstadistica(
    emoji: String,       // símbolo visual (📍, ✍️, ⭐)
    cantidad: String,    // número a mostrar
    etiqueta: String,    // texto descriptivo debajo
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = emoji, fontSize = 22.sp)
            Text(
                text = cantidad,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = etiqueta,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ── Componente: fila de información del perfil ──
// Recibe un ícono de Material, una etiqueta y un valor
@Composable
fun FilaInformacion(
    icono: ImageVector,  // ícono de Material Icons
    etiqueta: String,    // texto pequeño (ej: "Correo")
    valor: String        // valor principal (ej: "juan@email.com")
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Círculo con el ícono de la fila
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icono,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Etiqueta pequeña y valor principal
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = etiqueta,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = valor,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}