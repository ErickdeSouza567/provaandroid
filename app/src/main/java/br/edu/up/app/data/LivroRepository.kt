package br.edu.up.app.data

import kotlinx.coroutines.flow.Flow

interface LivroRepository {

    val livros: Flow<List<Livro>>
    suspend fun salvar(livro: Livro)
    suspend fun excluir(livro: Livro)
    suspend fun excluirTodos()

}
