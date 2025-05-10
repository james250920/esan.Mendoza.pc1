package esan.mendoza.pc01.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

data class Producto(
    val nombre: String,
    val precio: Double,
    val categoria: String,
    val imagenUrl: String
)

@Preview
@Composable
fun CatalogoProductosTecnológicoScreen() {
    val productos = remember {
        listOf(
            Producto("Laptop Dell XPS 13", 4500.0, "Laptop", "https://example.com/laptop.jpg"),
            Producto("iPhone 14 Pro", 5200.0, "Smartphone", "https://example.com/iphone.jpg"),
            Producto("Audífonos Sony WH-1000XM4", 1200.0, "Accesorio", "https://example.com/audifonos.jpg"),
            Producto("Monitor LG UltraWide", 1800.0, "Accesorio", "https://example.com/monitor.jpg")
        )
    }

    val totalPrecio = productos.sumOf { it.precio }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(productos.size) { index ->
                val producto = productos[index]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberImagePainter(data = producto.imagenUrl),
                            contentDescription = producto.nombre,
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 16.dp),
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(
                                text = producto.nombre,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Categoría: ${producto.categoria}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Precio: S/ ${producto.precio}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Total acumulado: S/ $totalPrecio",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )
    }
}