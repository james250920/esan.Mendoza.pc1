package esan.mendoza.pc01.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun CalculadoraEdadCaninaScreen() {
    var edad by remember { mutableStateOf("") }
    var tamaño by remember { mutableStateOf("Pequeño") }
    var resultado by remember { mutableStateOf<String?>(null) }
    val opcionesTamaño = listOf("Pequeño", "Mediano", "Grande")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de entrada para la edad
        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad del perro (años humanos)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para seleccionar el tamaño
        var expanded by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(tamaño)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                opcionesTamaño.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            tamaño = opcion
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para calcular
        Button(
            onClick = {
                val edadNumerica = edad.toIntOrNull()
                if (edadNumerica == null || edadNumerica <= 0) {
                    resultado = "Por favor, ingresa una edad válida (número positivo)."
                } else {
                    val edadPerro = when (tamaño) {
                        "Pequeño" -> edadNumerica * 5
                        "Mediano" -> edadNumerica * 6
                        "Grande" -> edadNumerica * 7
                        else -> 0
                    }
                    resultado = "¡Tu perro tiene $edadPerro años perro! 🐶"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el resultado
        resultado?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}