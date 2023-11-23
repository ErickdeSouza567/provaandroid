package br.edu.up.app.ui.livro

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import br.edu.up.app.databinding.FragmentListaLivrosBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LivrosFragment : Fragment() {

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModel: LivroViewModel by activityViewModels()

        val binding = FragmentListaLivrosBinding.inflate(layoutInflater)
        val recyclerView = binding.root

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.livros.collect { livros ->
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    recyclerView.adapter = LivroAdapter(livros, viewModel)
                }
            }
        }
        return binding.root
    }
}
