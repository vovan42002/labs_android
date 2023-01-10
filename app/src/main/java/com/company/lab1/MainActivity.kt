package com.company.lab1

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val levels = resources.getStringArray(R.array.levels)
        val spinner: Spinner = findViewById(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                R.layout.spinner_list,
                levels
            )
            spinner.adapter = adapter
        }

        val btnStart: Button = findViewById(R.id.btn_start)

        btnStart.setOnClickListener {
            val bundle = Bundle()
            when (spinner.selectedItem.toString()) {
                "Low" -> bundle.putInt("N", 4)
                "Medium" -> bundle.putInt("N", 5)
                "Hard" -> bundle.putInt("N", 6)
            }
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        val btnAbout: Button = findViewById(R.id.btn_about)
        btnAbout.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        val btnExit: Button = findViewById(R.id.btn_exit)
        btnExit.setOnClickListener {
            this.finish()
            System.exit(0)
        }
    }
}