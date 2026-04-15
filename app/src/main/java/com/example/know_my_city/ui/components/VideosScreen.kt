package com.example.know_my_city.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

// ── Modelo de datos para cada video de un lugar turístico ──
data class VideoLugar(
    val nombre: String,       // nombre del lugar turístico
    val descripcion: String,  // descripción corta del lugar
    val urlYoutube: String,   // enlace que se abre en el navegador
    val urlMiniatura: String, // imagen de vista previa
    val duracion: String      // duración aproximada del video
)

// ── Lista de lugares turísticos de Bogotá con sus videos ──
val lugaresBogota = listOf(
    VideoLugar(
        nombre = "Monserrate",
        descripcion = "Cerro emblemático con vista panorámica de toda la ciudad",
        urlYoutube = "https://www.youtube.com/watch?v=k5bVf-nRwp4",
        urlMiniatura = "https://cdn.pixabay.com/photo/2018/07/09/19/55/colombia-3527071_1280.jpg?w=600",
        duracion = "5:32"
    ),
    VideoLugar(
        nombre = "Parque 93",
        descripcion = "Zona gastronómica y de entretenimiento en el norte de la ciudad",
        urlYoutube = "https://www.youtube.com/results?search_query=parque+93+bogota",
        urlMiniatura = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/0f/71/78/1a/parque-93.jpg?w=600",
        duracion = "4:15"
    ),
    VideoLugar(
        nombre = "Parque Simón Bolívar",
        descripcion = "El pulmón verde más grande de Bogotá, ideal para recreación",
        urlYoutube = "https://www.youtube.com/watch?v=dJwA-22mrVI",
        urlMiniatura = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/07/32/11/40/parque-central-simon.jpg?w=600",
        duracion = "6:48"
    ),
    VideoLugar(
        nombre = "Museo del Oro",
        descripcion = "Más de 55.000 piezas de orfebrería precolombina únicas en el mundo",
        urlYoutube = "https://www.youtube.com/watch?v=k7aYm19k06I",
        urlMiniatura = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/b4/9b/52/museo-del-oro.jpg?w=600",
        duracion = "7:20"
    ),
    VideoLugar(
        nombre = "La Candelaria",
        descripcion = "El corazón histórico y colonial donde nació Bogotá",
        urlYoutube = "https://www.youtube.com/watch?v=tEUxX6j8eA8",
        urlMiniatura = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/24/db/8f/5a/plaza-de-bolivar-la-candelaria.jpg?w=600",
        duracion = "8:05"
    ),
    VideoLugar(
        nombre = "Usaquén",
        descripcion = "Barrio colonial con mercado de pulgas y los mejores restaurantes",
        urlYoutube = "https://www.youtube.com/watch?v=6NCdev_Pz9A",
        urlMiniatura = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/6a/6a/f0/de-kerk.jpg?w=600",
        duracion = "5:50"
    ),
    VideoLugar(
        nombre = "Jardín Botánico",
        descripcion = "Reserva natural con más de 4.000 especies de plantas nativas",
        urlYoutube = "https://www.youtube.com/watch?v=XZCdo2oqeVg",
        urlMiniatura = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/15/73/c4/51/jardin-botanico-de-bogota.jpg?w=600",
        duracion = "4:40"
    ),
    VideoLugar(
        nombre = "Ciclovía de Bogotá",
        descripcion = "550 km de vías recreativas habilitadas cada domingo y festivo",
        urlYoutube = "https://www.youtube.com/watch?v=9hZm1Ls3SOM",
        urlMiniatura = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/58/2d/5b/photo2jpg.jpg?w=600",
        duracion = "3:55"
    ),
    VideoLugar(
        nombre = "Zona Rosa",
        descripcion = "El epicentro de la moda, el comercio y la vida nocturna bogotana",
        urlYoutube = "https://www.youtube.com/watch?v=TYmBG4NuUZU",
        urlMiniatura = "https://hemmabogota.com/wp-content/uploads/2024/08/Zonta-T-articulo-Planes-pareja-norte-bogota.png?w=600",
        duracion = "4:10"
    ),
    VideoLugar(
        nombre = "Teleférico de Monserrate",
        descripcion = "Sube en teleférico o funicular hasta los 3.152 metros de altura",
        urlYoutube = "https://www.youtube.com/watch?v=2vMyX8HH53s",
        urlMiniatura = "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1d/32/9c/8d/caption.jpg?w=600",
        duracion = "6:00"
    )
)

@Composable
fun VideosScreen() {

    // Contexto necesario para lanzar el Intent que abre el navegador
    val contexto = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // ── Encabezado con degradado (igual al de las otras pantallas) ──
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A237E), // azul oscuro arriba
                            Color(0xFF0277BD)  // azul cielo abajo
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Ícono decorativo de ubicación
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )

                // Título principal del encabezado
                Text(
                    text = "Explora Bogotá",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Cantidad de lugares disponibles
                Text(
                    text = "${lugaresBogota.size} lugares para descubrir",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        // ── Lista vertical de tarjetas de video ──
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(lugaresBogota) { lugar ->
                // Cada tarjeta abre YouTube al ser tocada
                TarjetaVideo(
                    lugar = lugar,
                    alTocar = {
                        // Abre el enlace en el navegador del dispositivo
                        val intento = Intent(Intent.ACTION_VIEW, Uri.parse(lugar.urlYoutube))
                        contexto.startActivity(intento)
                    }
                )
            }
        }
    }
}

// ── Componente reutilizable: tarjeta de cada video ──
// Muestra miniatura con play, nombre, descripción y duración
@Composable
fun TarjetaVideo(
    lugar: VideoLugar,
    alTocar: () -> Unit  // acción al tocar la tarjeta completa
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { alTocar() }, // toda la tarjeta es clickeable
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column {

            // ── Sección de miniatura con botón de play encima ──
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentAlignment = Alignment.Center
            ) {
                // Imagen de vista previa del lugar
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(lugar.urlMiniatura)
                        .crossfade(true) // animación suave al cargar
                        .build(),
                    contentDescription = "Miniatura de ${lugar.nombre}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                )

                // Capa oscura semitransparente para mejorar legibilidad del play
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.Black.copy(alpha = 0.35f))
                )

                // Botón circular de play centrado sobre la miniatura
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.92f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = "Reproducir video de ${lugar.nombre}",
                        tint = Color(0xFF1565C0),
                        modifier = Modifier.size(32.dp)
                    )
                }

                // Etiqueta de duración en la esquina inferior derecha
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(8.dp),
                    shape = RoundedCornerShape(6.dp),
                    color = Color.Black.copy(alpha = 0.72f)
                ) {
                    Text(
                        text = lugar.duracion,
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            // ── Información del lugar debajo de la miniatura ──
            Column(
                modifier = Modifier.padding(14.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                // Nombre del lugar turístico
                Text(
                    text = lugar.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Descripción corta del lugar
                Text(
                    text = lugar.descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Start,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Indicador de que el video abre en YouTube
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = Color(0xFF1565C0),
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Ver en YouTube",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color(0xFF1565C0),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}