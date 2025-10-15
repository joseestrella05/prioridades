package edu.ucne.prioridades.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrioridadListScreen(viewModel: PrioridadViewModel) {
    val prioridades by viewModel.prioridades.collectAsState()
    val mensaje by viewModel.mensaje.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(mensaje) {
        mensaje?.let {
            scope.launch {
                snackbarHostState.showSnackbar(it)
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.cargarPrioridades()
    }

    Log.d("PrioridadListScreen", "Renderizando ${prioridades.size} elementos")

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Lista de Prioridades",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Buscar prioridad") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            val filtered = if (searchText.isNotBlank()) {
                prioridades.filter {
                    it.descripcion.contains(searchText, ignoreCase = true)
                }
            } else {
                prioridades
            }

            if (filtered.isEmpty()) {
                Text(
                    text = "No hay prioridades que mostrar.",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filtered) { p ->
                            PrioridadCard(
                                id = p.prioridadId ?: 0,
                                descripcion = p.descripcion,
                                tiempo = p.tiempo
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PrioridadCard(id: Int, descripcion: String, tiempo: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("ID: $id")
            Text("Descripción: $descripcion")
            Text("Tiempo: $tiempo horas")
        }
    }
}