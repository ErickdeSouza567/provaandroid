// GalleryViewModel.kt
package br.edu.up.app.ui.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.app.data.BancoSQLite
import br.edu.up.app.data.Horario
import br.edu.up.app.data.HorarioRepositorySqlite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class GalleryViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: HorarioRepositorySqlite
    val ultimaData: Flow<Horario?> // Alterado para Flow

    init {
        val horarioDao = BancoSQLite.get(application).horarioDao()
        repository = HorarioRepositorySqlite(horarioDao)
        ultimaData = repository.ultimaData
    }

    fun salvarData(data: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.inserirData(Horario(dataHora = data))

        }
    }

    suspend fun obterUltimaData(): Horario? {
        var horario: Horario? = null
        viewModelScope.launch(Dispatchers.IO) {
            horario = repository.obterUltimaData()
        }.join()
        return horario
    }

    suspend fun apagarTodasAsDatas(): String {
        var mensagem = ""
        viewModelScope.launch(Dispatchers.IO) {
            mensagem = repository.apagarTodasAsDatas()
        }.join()
        return mensagem
    }
}
