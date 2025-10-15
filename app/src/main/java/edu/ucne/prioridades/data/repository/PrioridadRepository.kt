package edu.ucne.prioridades.data.repository


import edu.ucne.prioridades.data.Model.Prioridad
import edu.ucne.prioridades.data.network.RetrofitClient

class PrioridadRepository {
    private val api = RetrofitClient.api

    suspend fun getPrioridades() = api.getPrioridades()
    suspend fun addPrioridad(prioridad: Prioridad) = api.addPrioridad(prioridad)
}
