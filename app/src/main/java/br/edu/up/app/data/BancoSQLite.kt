package br.edu.up.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Livro::class, Horario::class], version = 1, exportSchema = false)
abstract class BancoSQLite : RoomDatabase() {

    abstract fun livroDao(): LivroDAO
    abstract fun horarioDao(): HorarioDAO
    companion object {

        @Volatile
        private var INSTANCIA: BancoSQLite? = null

        fun get(context: Context): BancoSQLite {
            if (INSTANCIA == null) {
                INSTANCIA = Room.databaseBuilder(
                    context.applicationContext,
                    BancoSQLite::class.java,
                    "meu_banconovoo.db"
                ).build()
            }
            return INSTANCIA as BancoSQLite
        }
    }
}
