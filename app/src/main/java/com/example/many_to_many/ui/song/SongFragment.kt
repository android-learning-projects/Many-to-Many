package com.example.many_to_many.ui.song

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.many_to_many.databinding.FragmentSongBinding
import com.example.many_to_many.ui.MusicViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class SongFragment : Fragment() {
    private var _binding: FragmentSongBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: MusicViewModel by viewModels()

    private val adapter = SongAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapter
    }
    override fun onResume() {
        super.onResume()

        viewModel.getAllSongs.observe(this, {
            Timber.d("onCreate: $it")
            adapter.update(it)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}