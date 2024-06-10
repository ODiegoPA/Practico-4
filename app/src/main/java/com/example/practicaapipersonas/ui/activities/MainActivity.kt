package com.example.practicaapipersonas.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityMainBinding
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.models.Libros
import com.example.practicaapipersonas.ui.adapters.LibroAdapter
import com.example.practicaapipersonas.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), LibroAdapter.OnLibrosClickListener {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupRecyclerView()
        setupViewModelListeners()
        setupEventListeners()

    }

    override fun onResume() {
        super.onResume()
        model.fetchListaPersonas()
    }

    private fun setupEventListeners() {
        binding.btnAgregarLibro.setOnClickListener{
            val intent = Intent(this, InsertarLibroActivity::class.java)
            startActivity(intent)
        }
        binding.btnVerGeneros.setOnClickListener{
            val intent = Intent(this, GenerosActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewModelListeners() {
        model.categoryList.observe(this) {
            val adapter = (binding.lstPersonas.adapter as LibroAdapter)
            adapter.updateData(it)
        }
    }


    private fun setupRecyclerView() {
        binding.lstPersonas.apply {
            Log.d("Texto","No")
            this.adapter = LibroAdapter(Libros(), this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onLibrosClick(libro: Libro) {
        val intent = Intent(this, LibroActivity::class.java)
        Log.d("id",libro.id.toString())
        intent.putExtra("id", libro.id)
        startActivity(intent)
    }

}