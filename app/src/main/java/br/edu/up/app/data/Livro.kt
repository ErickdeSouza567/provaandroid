package br.edu.up.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "livros")
data class Livro(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var docId: String,
    var titulo: String,
    var descricao: String = String(),
    var preco: Double = 0.0,
    var autor: String = "Desconhecido",
    var foto: String = "semfoto.jpg",
    var categoria: Int = 0
) {
    constructor() : this(0,"","","",0.0,"Desconhecido","semfoto.jpg",0)
}
