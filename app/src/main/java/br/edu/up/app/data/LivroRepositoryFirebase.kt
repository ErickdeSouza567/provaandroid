package br.edu.up.app.data

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class LivroRepositoryFirebase
@Inject constructor(val livrosRef : CollectionReference): LivroRepository {

    private var _livros = MutableStateFlow(listOf<Livro>())
    override val livros: Flow<List<Livro>> get() = _livros.asStateFlow()

    init {
        livrosRef.addSnapshotListener { snapshot, _ ->
            if (snapshot == null){
                _livros.value = listOf()
            } else {
                var livros = mutableListOf<Livro>()
                snapshot.documents.forEach { doc ->
                    val livro = doc.toObject<Livro>()
                    if (livro != null){
                        livro.docId = doc.id
                        livros.add(livro)
                    }
                }
                _livros.value = livros
            }

        }
    }

    override suspend fun salvar(livro: Livro) {
        if(livro.docId.isNullOrEmpty()){
            var doc = livrosRef.document()
            livro.docId = doc.id
            doc.set(livro)
        } else {
            livrosRef.document(livro.docId).set(livro)
        }
    }

    override suspend fun excluir(livro: Livro) {
        livrosRef.document(livro.docId).delete()
    }

    override suspend fun excluirTodos() {
        _livros.collect { livros ->
            livros.forEach { livro ->
                livrosRef.document(livro.docId).delete()
            }
        }
    }
}
