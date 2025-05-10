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
import java.text.DecimalFormat

@Preview
@Composable
fun ConversorDivisaScreen() {
    var monto by remember { mutableStateOf("") }
    var tipoConversion by remember { mutableStateOf("USD a PEN") }
    var resultado by remember { mutableStateOf<String?>(null) }
    val opcionesConversion = listOf("USD a PEN", "PEN a USD")
    val tasaCambio = 3.80
    val decimalFormat = DecimalFormat("#.##")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Campo de entrada para el monto
        OutlinedTextField(
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto a convertir") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown para seleccionar el tipo de conversión
        var expanded by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(tipoConversion)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                opcionesConversion.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            tipoConversion = opcion
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
                val montoNumerico = monto.toDoubleOrNull()
                if (montoNumerico == null || montoNumerico <= 0) {
                    resultado = "Por favor, ingresa un monto válido (número positivo)."
                } else {
                    resultado = when (tipoConversion) {
                        "USD a PEN" -> {
                            val conversion = montoNumerico * tasaCambio
                            "Resultado: S/ ${decimalFormat.format(conversion)}"
                        }
                        "PEN a USD" -> {
                            val conversion = montoNumerico / tasaCambio
                            "Resultado: $ ${decimalFormat.format(conversion)}"
                        }
                        else -> null
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Convertir")
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