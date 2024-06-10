package com.example.practicaapipersonas.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityLibroBinding
import com.example.practicaapipersonas.databinding.ActivityMainBinding
import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.ui.viewmodels.LibroViewModel

class LibroActivity : AppCompatActivity() {
    private var id: Int = -1
    private val model:LibroViewModel by viewModels()
    lateinit var binding: ActivityLibroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        id = intent.getIntExtra("id", -1)
        if (id != -1) {
            model.loadCategory(id)
        }
        setupBindings()
        setupEventListeners()
    }
    override fun onResume() {
        super.onResume()
        if (id != -1) {
            model.loadCategory(id)
        }
    }



    private fun setupBindings() {
        model.libro.observe(this) {
            if (it == null) {
                Log.d("esNulo","Si")
                return@observe
            }
            binding.txtNombreLibro.text = it.nombre
            binding.txtAutorLibro.text = it.autor
            binding.txtISBNLibro.text = it.isbn
            binding.txtCalificacionLibro.text = it.calificacion.toString()
            binding.txtSinopsisLibro.text = it.sinopsis
            binding.txtEditorialLibro.text = it.editorial
            Glide.with(this)
                .load(it.imagen)
                .override(300, 300)
                .into(binding.imgCoverLibro)
        }
        model.closeActivity.observe(this) {
            if (it) {
                finish()

            }
        }
    }
    private fun setupEventListeners() {
        Log.d("ID antes del setup enviar", id.toString())
        binding.btnEditar.setOnClickListener {
            val intent = Intent(this, InsertarLibroActivity::class.java)
            Log.d("ID al enviar", id.toString())
            intent.putExtra("id", id)
            startActivity(intent)
        }
        binding.btnBorrar.setOnClickListener{
            model.deleteLibro(id)
        }
    }
}