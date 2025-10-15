package edu.ucne.prioridades.data.network

import edu.ucne.prioridades.data.Model.Prioridad
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("PrioridadesApi")
    suspend fun getPrioridades(): Response<List<Prioridad>>

    @POST("PrioridadesApi")
    suspend fun addPrioridad(@Body prioridad: Prioridad): Response<Prioridad>
}