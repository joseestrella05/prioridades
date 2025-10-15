package edu.ucne.prioridades.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import edu.ucne.prioridades.presentation.PrioridadViewModel

@Composable
fun PrioridadFormScreen(viewModel: PrioridadViewModel) {
    var descripcion by remember { mutableStateOf("") }
    var tiempo by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Agregar Prioridad",
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = tiempo,
            onValueChange = { tiempo = it },
            label = { Text("Tiempo") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (descripcion.isNotBlank() && tiempo.isNotBlank()) {
                    viewModel.agregarPrioridad(descripcion, tiempo.toInt())
                    descripcion = ""
                    tiempo = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Prioridad")
        }
    }
}