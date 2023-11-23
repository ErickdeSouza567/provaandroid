package br.edu.up.app.ui.livro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import br.edu.up.app.data.Livro
import br.edu.up.app.databinding.FragmentLivroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LivroFragment : Fragment() {

    private val viewModel: LivroViewModel by activityViewModels()
    private lateinit var binding: FragmentLivroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLivroBinding.inflate(layoutInflater)

        val livro = viewModel.livro
        binding.inputTitulo.setText(livro.titulo)
        binding.inputDescricao.setText(livro.descricao)
        binding.inputPreco.setText(livro.preco.toString())
        binding.inputFoto.setText(livro.foto)
        binding.inputAutor.setText(livro.autor) // Adicione esta linha para o campo autor

        binding.btnSalvar.setOnClickListener {
            val livroSalvar = Livro(
                livro.id,
                livro.docId,
                binding.inputTitulo.text.toString(),
                binding.inputDescricao.text.toString(),
                binding.inputPreco.text.toString().toDouble(),
                binding.inputAutor.text.toString(),
                binding.inputFoto.text.toString(),
                0
            )
            viewModel.livro = livroSalvar
            viewModel.salvar()
            findNavController().popBackStack()
        }

        return binding.root
    }
}
