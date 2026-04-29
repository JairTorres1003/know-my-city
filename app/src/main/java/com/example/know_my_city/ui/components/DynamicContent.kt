package com.example.know_my_city.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 60.dp, start = 24.dp, end = 24.dp, bottom = 40.dp)
        ) {

            AsyncImage(
                model = "https://images.unsplash.com/photo-1731560816331-bdbb9f61323a?q=80&w=764&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                contentDescription = "Logo Ciudad",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
            )

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

            Text(
                text = "Bienvenido a Bogotá",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.12f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val infoList = listOf(
                        "Nombre" to "Bogotá D.C.",
                        "País" to "Colombia",
                        "Fundación" to "6 de agosto de 1538",
                        "Altitud" to "2.640 m sobre el nivel del mar",
                        "Área" to "~1.775 km²",
                        "Población" to "Más de 8 millones de habitantes en la ciudad y cerca de 12 millones en el área metropolitana."
                    )

                    infoList.forEach { (label, value) ->
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.White)) {
                                    append("$label: ")
                                }
                                withStyle(style = SpanStyle(color = Color.White.copy(alpha = 0.9f))) {
                                    append(value)
                                }
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp
                        )
                    }
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(horizontal = 4.dp)
            ) {
                Text(
                    text = "Bogotá fue originalmente un territorio habitado por los muiscas antes de la llegada de los españoles. En 1538 fue fundada por Gonzalo Jiménez de Quesada y se convirtió en uno de los centros políticos más importantes de América del Sur durante la época colonial.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Justify,
                    lineHeight = 22.sp
                )

                Text(
                    text = "Actualmente es la capital de Colombia y uno de los centros económicos, culturales y tecnológicos más importantes de Latinoamérica.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Justify,
                    lineHeight = 22.sp
                )
            }

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
                        HorizontalDivider(
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
        }
    }
}
