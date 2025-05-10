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
            Producto("Laptop Dell XPS 13", 4500.0, "Laptop", "https://media.falabella.com/falabellaPE/132241996_01/w=1500,h=1500,fit=pad"),
            Producto("iPhone 14 Pro", 5200.0, "Smartphone", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/HP_Elite_x3_%2826600260562%29.jpg/500px-HP_Elite_x3_%2826600260562%29.jpg"),
            Producto("Audífonos Sony WH-1000XM4", 1200.0, "Accesorio", "https://www.jbl.com.pe/dw/image/v2/AAUJ_PRD/on/demandware.static/-/Sites-masterCatalog_Harman/default/dwecf2f72f/JBL_TOUR_One%20M2_Product%20Image_Hero_Black.jpg?sw=270&sh=330&sm=fit&sfrm=png"),
            Producto("Monitor LG UltraWide", 1800.0, "Accesorio", "https://www.jbl.com.pe/dw/image/v2/AAUJ_PRD/on/demandware.static/-/Sites-masterCatalog_Harman/default/dwb27d0119/JBL4305P_System_Front_Black_WithRemote_MPHiRez_WEBBLACKHERO.jpg?sw=270&sh=330&sm=fit&sfrm=png")
        )
    }

    val totalPrecio = productos.sumOf { it.precio }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(bottom = 16.dp),
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