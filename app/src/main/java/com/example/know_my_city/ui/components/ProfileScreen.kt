package com.example.know_my_city.ui.components

// ============================================================
// IMPORTS DE LAYOUT (cómo se organizan los elementos)
// ============================================================

// background() → permite poner color de fondo a cualquier elemento
import androidx.compose.foundation.background

// fillMaxSize, fillMaxWidth → ocupar todo el ancho/alto disponible
// height, size, offset → definir tamaños y posiciones específicas
// padding → espacio interno entre el borde y el contenido
// Arrangement → cómo distribuir elementos (espaciado, centrado, etc)
import androidx.compose.foundation.layout.*

// ============================================================
// IMPORTS DE FORMA (para recortar en círculo u otras formas)
// ============================================================

// CircleShape → forma circular, se usa para la foto de perfil
import androidx.compose.foundation.shape.CircleShape

// ============================================================
// IMPORTS DE ÍCONOS
// ============================================================

// Icons → la colección de íconos de Material Design
import androidx.compose.material.icons.Icons

// Icons.Default.Email → ícono de sobre/correo
import androidx.compose.material.icons.filled.Email

// Icons.Default.Person → ícono de persona/usuario
import androidx.compose.material.icons.filled.Person

// ============================================================
// IMPORTS DE COMPONENTES VISUALES (Material Design 3)
// ============================================================

// Button → botón con fondo de color (el de "Editar perfil")
// HorizontalDivider → línea separadora horizontal
// Icon → muestra un ícono de Material Icons
// MaterialTheme → accede a colores, tipografías y formas del tema
// Surface → contenedor con fondo, bordes y elevación
// Text → muestra texto en pantalla
import androidx.compose.material3.*

// ============================================================
// IMPORT ESENCIAL — sin este nada funciona
// ============================================================

// @Composable → marca una función como "dibujable" en pantalla
// Sin esta anotación, Kotlin no sabe que la función es una vista
import androidx.compose.runtime.Composable

// ============================================================
// IMPORTS DE MODIFICADORES (modifier = cómo se ve/comporta)
// ============================================================

// Alignment → alinear elementos (Centro, Arriba, Izquierda, etc)
import androidx.compose.ui.Alignment

// Modifier → base de todos los modificadores visuales
// Sin esto no puedes usar .fillMaxSize(), .padding(), etc.
import androidx.compose.ui.Modifier

// clip() → recorta un elemento con una forma (ej: en círculo)
import androidx.compose.ui.draw.clip

// ============================================================
// IMPORTS DE TEXTO
// ============================================================

// FontWeight → grosor del texto (Medium, Bold, Light, etc)
import androidx.compose.ui.text.font.FontWeight

// ============================================================
// IMPORTS DE UNIDADES DE MEDIDA
// ============================================================

// dp → unidad de tamaño para layouts (márgenes, tamaños, etc)
//      dp se adapta a diferentes densidades de pantalla
import androidx.compose.ui.unit.dp

// sp → unidad de tamaño para textos
//      sp respeta el tamaño de fuente del sistema del usuario
import androidx.compose.ui.unit.sp
@Composable
fun ProfileScreen() {

    // Column pone los elementos uno debajo del otro
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        // --- BANNER SUPERIOR con iniciales ---
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.BottomCenter
        ) {
            // Círculo con iniciales del usuario
            Box(
                modifier = Modifier
                    .offset(y = 36.dp) // lo empuja hacia abajo del banner
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "JD",           // iniciales del usuario
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Espacio para que el círculo no tape el nombre
        Spacer(modifier = Modifier.height(44.dp))

        // --- NOMBRE Y CORREO ---
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Juan Díaz",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "juan.diaz@email.com",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(modifier = Modifier.padding(horizontal = 24.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // --- TARJETAS DE INFORMACIÓN ---
        Column(
            modifier = Modifier.padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Tarjeta: Nombre
            InfoCard(
                icon = { Icon(Icons.Default.Person, contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary) },
                label = "Nombre",
                value = "Juan Díaz"
            )

            // Tarjeta: Correo
            InfoCard(
                icon = { Icon(Icons.Default.Email, contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary) },
                label = "Correo",
                value = "juan.diaz@email.com"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- BOTÓN EDITAR ---
        Button(
            onClick = { /* acción futura */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(48.dp)
        ) {
            Text(text = "Editar perfil")
        }
    }
}

// Componente reutilizable para cada fila de información
// Recibe un ícono, una etiqueta y un valor
@Composable
fun InfoCard(
    icon: @Composable () -> Unit,  // el ícono que se muestra
    label: String,                  // texto pequeño arriba (ej: "Nombre")
    value: String                   // valor principal (ej: "Juan Díaz")
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Círculo pequeño con el ícono
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                icon() // dibuja el ícono que se pasó
            }

            // Etiqueta y valor
            Column {
                Text(
                    text = label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}