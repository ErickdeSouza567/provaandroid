package br.edu.up.app

import android.app.Application
import android.content.Context
import br.edu.up.app.data.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@HiltAndroidApp
@InstallIn(SingletonComponent::class)
class AppLocadora : Application() {

    @Provides
    fun provideLivrosRef() : CollectionReference {
        return Firebase.firestore.collection("livros")
    }

    @Provides
    fun provideLivroRepositoryFirebase(livrosRef: CollectionReference)
            : LivroRepository{
        return LivroRepositoryFirebase(livrosRef)
    }

    @Provides
    fun provideLivroRepositorySqlite(livroDAO: LivroDAO) : LivroRepositorySqlite {
        return LivroRepositorySqlite(livroDAO)
    }

    @Provides
    fun provideLivroDAO(bancoSQLite: BancoSQLite): LivroDAO {
        return bancoSQLite.livroDao()
    }

    @Provides
    @Singleton
    fun provideBanco(@ApplicationContext ctx: Context): BancoSQLite {
        return BancoSQLite.get(ctx)
    }
}
