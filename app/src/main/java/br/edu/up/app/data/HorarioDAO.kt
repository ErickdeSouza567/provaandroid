package br.edu.up.app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HorarioDAO {
    @Query("SELECT * FROM Horario ORDER BY id DESC LIMIT 1")
    fun obterUltimaData(): Flow<Horario?>

    @Insert
    fun inserirData(horario: Horario)

    @Query("delete from horario")
    suspend fun excluirTodosdatas()
}