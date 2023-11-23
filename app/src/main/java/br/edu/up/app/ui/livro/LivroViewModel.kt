package br.edu.up.app.ui.livro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.up.app.data.Livro
import br.edu.up.app.data.LivroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LivroViewModel
@Inject constructor(val repository: LivroRepository) : ViewModel() {

    var livro: Livro = Livro()

    private var _livros = MutableStateFlow(listOf<Livro>())
    val livros: Flow<List<Livro>> = _livros

    init {
        viewModelScope.launch {
            repository.livros.collect { livros ->
                _livros.value = livros
            }
        }
    }

    fun novo() {
        this.livro = Livro()
    }

    fun editar(livro: Livro) {
        this.livro = livro
    }

    fun salvar() = viewModelScope.launch {
        repository.salvar(livro)
    }

    fun excluir(livro: Livro) = viewModelScope.launch {
        repository.excluir(livro)
    }
}
