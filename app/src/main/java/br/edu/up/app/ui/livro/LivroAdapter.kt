package br.edu.up.app.ui.livro

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import br.edu.up.app.R
import br.edu.up.app.data.Livro
import br.edu.up.app.databinding.FragmentItemLivroBinding
import coil.load
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class LivroAdapter(
    private val livros: List<Livro>,
    val viewModel: LivroViewModel
) : RecyclerView.Adapter<LivroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentItemLivroBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val livro = livros[position]

        // Altere a propriedade nome para titulo
        holder.txtNome.text = livro.titulo
        holder.txtPreco.text = livro.preco.toString()

        // Carregamento remoto
        holder.imgFoto.load(R.drawable.semfoto) // Altere para o seu recurso de imagem padrão
        Firebase.storage.getReference(livro.foto)
            .downloadUrl.addOnSuccessListener { imageUrl ->
                holder.imgFoto.load(imageUrl)
            }

        holder.itemView.setOnClickListener { view ->
            viewModel.editar(livro)
            val action = LivrosFragmentDirections.actionNavHomeToProdutoFragment()
            view.findNavController().navigate(action)
        }

        holder.itemView.setOnLongClickListener { view ->
            AlertDialog.Builder(view.context)
                .setMessage("ATENÇÃO: Tem certeza que deseja excluir?")
                .setPositiveButton("Confirmar") { dialog, id ->
                    viewModel.excluir(livro)
                }
                .setNegativeButton("CANCELAR") { dialog, id ->
                    // Ignorar
                }
                .create()
                .show()
            true
        }
    }

    override fun getItemCount(): Int = livros.size

    inner class ViewHolder(binding: FragmentItemLivroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val imgFoto: ImageView = binding.imgFoto
        val txtNome: TextView = binding.txtNome
        val txtPreco: TextView = binding.txtPreco
    }
}
