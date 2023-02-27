package com.ragtag.ttub.ui.sheets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ragtag.ttub.databinding.FragmentSheetsBinding


class SheetsFragment : Fragment() {
    private var _binding: FragmentSheetsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val sheetsViewModel =
            ViewModelProvider(this).get(SheetsViewModel::class.java)

        _binding = FragmentSheetsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textSheets
        sheetsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}