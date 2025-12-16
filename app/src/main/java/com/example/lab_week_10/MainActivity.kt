package com.example.lab_week_10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lab_week_10.viewmodels.TotalViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TotalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[TotalViewModel::class.java]

        val textTotal = findViewById<TextView>(R.id.text_total)
        val buttonIncrement = findViewById<Button>(R.id.button_increment)

        viewModel.total.observe(this) { total ->
            textTotal.text = getString(R.string.text_total, total)
        }

        buttonIncrement.setOnClickListener {
            viewModel.incrementTotal()
        }
    }
}
