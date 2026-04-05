package com.example.know_my_city.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

// Lista de fotos de Bogotá desde Wikimedia (gratuitas y sin API key)
val fotosBogota = listOf(
    "https://images.unsplash.com/photo-1720067392108-89b9485aa090?q=80&w=685&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://plus.unsplash.com/premium_photo-1697730030651-3a7aa391b9d6?q=80&w=688&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1681145552723-02f3c3e5b6a6?q=80&w=628&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://plus.unsplash.com/premium_photo-1697730219869-0a63029c52a7?q=80&w=1170&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1731560816331-bdbb9f61323a?q=80&w=764&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
)

@Composable
fun fotosCiudad() {

    val context = LocalContext.current

    // verticalScroll permite hacer scroll en toda la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // --- TÍTULO ---
        Text(
            text = "Bogotá, Colombia",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
        )

        // --- MAPA ---
        AndroidView(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(16.dp)), // bordes redondeados al mapa
            factory = { ctx ->
                Configuration.getInstance().userAgentValue = ctx.packageName //Aquí se configura el user agent para que la API de OpenStreetMap funcione
                MapView(ctx).apply { //Aquí se configura el mapa
                    setTileSource(TileSourceFactory.MAPNIK) //Aquí se configura el tile source para que se muestre el mapa
                    setMultiTouchControls(true) //Aquí se configura el control multi-touch para que se pueda hacer zoom con dos dedos
                    val bogota = GeoPoint(4.7110, -74.0721) //Aquí se configura la ubicación de Bogotá
                    controller.setZoom(12.0) //Aquí se configura el zoom
                    controller.setCenter(bogota) //Aquí se configura el centro del mapa
                    val marker = Marker(this) //Aquí se configura el marcador
                    marker.position = bogota //Aquí se configura la posición del marcador
                    marker.title = "Bogotá, Colombia" //Aquí se configura el título del marcador
                    marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM) //Aquí se configura el anclaje del marcador
                    overlays.add(marker) //Aquí se añade el marcador al mapa
                }
            }
        )

        // --- SECCIÓN DE FOTOS ---
        Text(
            text = "Galería de fotos",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        // LazyRow muestra las fotos en fila horizontal con scroll
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(fotosBogota) { url ->
                // Cada foto en una card con bordes redondeados
                Card(
                    modifier = Modifier
                        .width(240.dp)
                        .height(160.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    // AsyncImage carga la imagen desde la URL automáticamente
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(url)          // URL de la imagen
                            .crossfade(true)    // animación suave al cargar
                            .build(),
                        contentDescription = "Foto de Bogotá",
                        contentScale = ContentScale.Crop, // recorta para llenar la card
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }

        // --- DESCRIPCIÓN DE BOGOTÁ ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                // color suave de fondo para la descripción
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Sobre Bogotá",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Bogotá es la vibrante capital de Colombia, " +
                            "ubicada a 2.600 metros sobre el nivel del mar. " +
                            "Ciudad de contrastes, mezcla historia colonial en " +
                            "La Candelaria con modernos barrios como Usaquén y Chapinero. " +
                            "Hogar de museos de clase mundial, gastronomía única " +
                            "y una cultura que la convierte en el corazón cultural " +
                            "de Latinoamérica.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Justify, // texto justificado
                    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                )
            }
        }
    }
}