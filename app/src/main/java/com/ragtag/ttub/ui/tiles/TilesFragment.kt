package com.ragtag.ttub.ui.tiles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ragtag.ttub.databinding.FragmentTilesBinding



class TilesFragment : Fragment() {

    private var _binding: FragmentTilesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val tilesViewModel =
            ViewModelProvider(this).get(TilesViewModel::class.java)

        _binding = FragmentTilesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textTiles
        tilesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

}