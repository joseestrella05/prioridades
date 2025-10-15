package edu.ucne.prioridades.domain

import edu.ucne.prioridades.data.Model.Prioridad
import edu.ucne.prioridades.data.repository.PrioridadRepository
import retrofit2.Response

class GetPrioridadesUseCase(
    private val repository: PrioridadRepository = PrioridadRepository()
) {
    suspend operator fun invoke(): Response<List<Prioridad>> {
        return repository.getPrioridades()
    }
}