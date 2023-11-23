package br.edu.up.app.data

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LivroRepositorySqlite
@Inject constructor(val livroDAO: LivroDAO) : LivroRepository {

    override val livros: Flow<List<Livro>> get() = livroDAO.listar()

    override suspend fun salvar(livro: Livro) {
        if (livro.id == 0) {
            livroDAO.inserir(livro)
        } else {
            livroDAO.atualizar(livro)
        }
    }

    override suspend fun excluir(livro: Livro) {
        livroDAO.excluir(livro)
    }

    override suspend fun excluirTodos() {
        livroDAO.excluirTodos()
    }
}
