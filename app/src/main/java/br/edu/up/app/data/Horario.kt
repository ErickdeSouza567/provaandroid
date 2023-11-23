package br.edu.up.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Horario(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val dataHora: Long // Representa a data e hora em milissegundos
)
