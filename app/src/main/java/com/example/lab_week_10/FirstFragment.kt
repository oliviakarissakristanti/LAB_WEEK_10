package com.example.lab_week_10

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel

class FirstFragment : Fragment(R.layout.fragment_first) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textTotal = view.findViewById<TextView>(R.id.text_total)

        // SHARE ViewModel dengan Activity
        val viewModel = ViewModelProvider(requireActivity())[TotalViewModel::class.java]

        viewModel.total.observe(viewLifecycleOwner) { total ->
            textTotal.text = getString(R.string.text_total, total)
        }
    }
}
