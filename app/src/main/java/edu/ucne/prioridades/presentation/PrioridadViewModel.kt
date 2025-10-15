package edu.ucne.prioridades.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.ucne.prioridades.data.Model.Prioridad
import edu.ucne.prioridades.domain.AddPrioridadUseCase
import edu.ucne.prioridades.domain.GetPrioridadesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

class PrioridadViewModel : ViewModel() {

    private val getPrioridadesUseCase = GetPrioridadesUseCase()
    private val addPrioridadUseCase = AddPrioridadUseCase()

    private val _prioridades = MutableStateFlow<List<Prioridad>>(emptyList())
    val prioridades: StateFlow<List<Prioridad>> get() = _prioridades

    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> get() = _mensaje

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun cargarPrioridades() {
        viewModelScope.launch {
            Log.d("Prioridades", "Haciendo GET...")
            val response = getPrioridadesUseCase()
            if (response.isSuccessful) {
                Log.d("Prioridades", "Respuesta código: ${response.code()}")
                val data = response.body() ?: emptyList()
                Log.d("Prioridades", "Lista recibida: ${data.size} elementos")
                _prioridades.value = data
            } else {
                _mensaje.value = "Error: ${response.code()}"
            }
        }
    }

    fun agregarPrioridad(descripcion: String, tiempo: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val nueva = Prioridad(descripcion = descripcion, tiempo = tiempo)
                val response = addPrioridadUseCase(nueva)

                if (response.isSuccessful) {
                    _mensaje.value = "Prioridad agregada correctamente"
                    cargarPrioridades() // refrescar lista
                } else {
                    _mensaje.value = "Error al guardar prioridad"
                }
            } catch (e: Exception) {
                _mensaje.value = "Error de conexión al guardar"
                Log.e("Prioridades", "Excepción POST", e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}