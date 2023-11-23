package br.edu.up.app.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LivroDAO {

    @Query("select * from livros")
    fun listar(): Flow<List<Livro>>

    @Insert
    suspend fun inserir(livro: Livro)

    @Update
    suspend fun atualizar(livro: Livro)

    @Delete
    suspend fun excluir(livro: Livro)

    @Query("delete from livros where id = :id")
    suspend fun excluir(id: Int)

    @Query("delete from livros")
    suspend fun excluirTodos()
}
