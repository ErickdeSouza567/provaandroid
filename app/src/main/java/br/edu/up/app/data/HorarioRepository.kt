// HorarioRepository.kt
package br.edu.up.app.data

import kotlinx.coroutines.flow.Flow

interface HorarioRepository {
    val ultimaData: Flow<Horario?>
    suspend fun inserirData(horario: Horario)
    suspend fun obterUltimaData(): Horario?
    suspend fun apagarTodasAsDatas(): String
}
