package com.example.lab_week_10

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.lab_week_10.database.Total
import com.example.lab_week_10.database.TotalDatabase
import com.example.lab_week_10.viewmodels.TotalViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: TotalViewModel
    private lateinit var db: TotalDatabase

    companion object {
        const val ID: Long = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[TotalViewModel::class.java]

        db = Room.databaseBuilder(
            applicationContext,
            TotalDatabase::class.java,
            "total-database"
        ).allowMainThreadQueries().build()

        initializeValueFromDatabase()

        val textTotal = findViewById<TextView>(R.id.text_total)
        val buttonIncrement = findViewById<Button>(R.id.button_increment)

        viewModel.total.observe(this) { total ->
            textTotal.text = getString(R.string.text_total, total)
        }

        buttonIncrement.setOnClickListener {
            viewModel.incrementTotal()
        }
    }

    private fun initializeValueFromDatabase() {
        val data = db.totalDao().getTotal(ID)
        if (data.isEmpty()) {
            db.totalDao().insert(Total(ID, 0))
        } else {
            viewModel.setTotal(data.first().total)
        }
    }

    override fun onPause() {
        super.onPause()
        db.totalDao().update(
            Total(ID, viewModel.total.value ?: 0)
        )
    }
}
