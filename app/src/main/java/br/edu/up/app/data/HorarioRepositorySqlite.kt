package br.edu.up.app.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class HorarioRepositorySqlite @Inject constructor(private val HorarioDao: HorarioDAO) : HorarioRepository {

    override val ultimaData: Flow<Horario?> = HorarioDao.obterUltimaData()

    override suspend fun inserirData(horario: Horario) {
        HorarioDao.inserirData(horario)
    }

    override suspend fun obterUltimaData(): Horario? {
        return HorarioDao.obterUltimaData().firstOrNull()
    }

    override suspend fun apagarTodasAsDatas(): String {
        HorarioDao.excluirTodosdatas()
        return "Datas limpas"
    }
}