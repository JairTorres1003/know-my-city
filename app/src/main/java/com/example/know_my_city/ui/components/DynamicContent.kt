package com.example.know_my_city.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.know_my_city.ui.ClimaViewModel

// Lista de datos curiosos que rotan cada vez que se abre la pantalla
val datosCuriosos = listOf(
    "Bogotá tiene más de 550 km de ciclovías, una de las redes más grandes del mundo.",
    "El Museo del Oro en Bogotá alberga más de 55.000 piezas de orfebrería precolombina.",
    "Bogotá está ubicada a 2.600 metros sobre el nivel del mar.",
    "La ciudad fue fundada el 6 de agosto de 1538 por Gonzalo Jiménez de Quesada.",
    "Bogotá es conocida como 'La Atenas Suramericana' por su tradición cultural."
)

@Composable
fun DynamicContent(
    modifier: Modifier = Modifier
) {
    // ── ViewModel del clima ──
    val modeloClima: ClimaViewModel = viewModel()
    val datosClima by modeloClima.clima.collectAsState()

    // ── Dato curioso aleatorio al abrir la pantalla ──
    val datoCurioso = remember { datosCuriosos.random() }

    // ── Animación del degradado de fondo ──
    val infiniteTransition = rememberInfiniteTransition(label = "fondoAnimado")
    val offsetAnimado by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 6000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "offsetDegradado"
    )

    // Colores del degradado animado
    val degradadoFondo = Brush.linearGradient(
        colors = listOf(
            Color(0xFF1A237E), // azul oscuro
            Color(0xFF283593), // azul medio
            Color(0xFF1565C0), // azul vivo
            Color(0xFF0277BD)  // azul cielo
        ),
        start = Offset(offsetAnimado, 0f),
        end = Offset(offsetAnimado + 800f, 1200f)
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(degradadoFondo) // fondo degradado animado
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(24.dp)
            ) {

                    // Ícono circular decorativo de ubicación
                    Box(
                        modifier = Modifier
                            .size(90.dp)
                            .clip(RoundedCornerShape(45.dp))
                            .background(Color.White.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(44.dp)
                        )
                    }

                    // Título principal de bienvenida
                    Text(
                        text = "Bienvenido a Bogotá",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    // Subtítulo descriptivo
                    Text(
                        text = "Explora la ciudad, su clima y sus lugares más icónicos",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.75f),
                        textAlign = TextAlign.Center,
                        lineHeight = 24.sp
                    )

                    // ── Tarjeta del clima actual ──
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.15f)
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            if (datosClima != null) {
                                // Temperatura actual
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "🌡️",
                                        fontSize = 28.sp
                                    )
                                    Text(
                                        text = "${datosClima!!.current_weather.temperature}°C",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Temperatura",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                }

                                // Divisor vertical
                                Divider(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(1.dp),
                                    color = Color.White.copy(alpha = 0.3f)
                                )

                                // Velocidad del viento
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(
                                        text = "💨",
                                        fontSize = 28.sp
                                    )
                                    Text(
                                        text = "${datosClima!!.current_weather.windspeed} km/h",
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Text(
                                        text = "Viento",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = Color.White.copy(alpha = 0.7f)
                                    )
                                }
                            } else {
                                // Mensaje mientras carga el clima
                                Text(
                                    text = "⏳ Cargando clima...",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                        }
                    }

                    // ── Tarjeta de dato curioso ──
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White.copy(alpha = 0.15f)
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            // Ícono del dato curioso
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text(
                                    text = "¿Sabías que...?",
                                    style = MaterialTheme.typography.labelLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                // Dato curioso aleatorio
                                Text(
                                    text = datoCurioso,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White.copy(alpha = 0.85f),
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }

                // Chip decorativo con la ubicación
                Surface(
                    shape = RoundedCornerShape(50.dp),
                    color = Color.White.copy(alpha = 0.2f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Bogotá, Colombia",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
