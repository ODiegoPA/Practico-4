package com.example.practicaapipersonas.ui.activities

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityInsertarLibroBinding
import com.example.practicaapipersonas.ui.viewmodels.InsertarLibroViewModel
import com.example.practicaapipersonas.ui.viewmodels.MainViewModel

class InsertarLibroActivity : AppCompatActivity() {
    private var id: Int = -1
    lateinit var binding: ActivityInsertarLibroBinding
    private val model: InsertarLibroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInsertarLibroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        id = intent.getIntExtra("id",-1)
        Log.d("Paso por el primer id","Si")
        if (id != -1) {
            model.loadLibro(id)
            Log.d("Paso por el primer id","Si")
        }
        setupViewModelObservers()
        setupEventListener()
    }



    private fun setupEventListener() {
        binding.btnInsertarLibro.setOnClickListener{
            model.saveLibro(
                id,
                binding.txtInsertNombre.editableText.toString(),
                binding.txtInsertAutor.editableText.toString(),
                binding.txtInsertEditorial.editableText.toString(),
                binding.txtInsertISBN.editableText.toString(),
                binding.txtInsertImagenURL.editableText.toString(),
                binding.txtInsertSinopsis.editableText.toString(),
                binding.txtInsertCalificacion.editableText.toString().toInt()
            )
        }
        model.libro.observe(this){
            if (it == null) {
                return@observe
            }
            binding.txtInsertNombre.setText(it.nombre)
            binding.txtInsertAutor.setText(it.autor)
            binding.txtInsertEditorial.setText(it.editorial)
            binding.txtInsertISBN.setText(it.isbn)
            binding.txtInsertImagenURL.setText(it.imagen)
            binding.txtInsertSinopsis.setText(it.sinopsis)
            binding.txtInsertCalificacion.setText(it.calificacion.toString())
        }
    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this){
            if(it){
                finish()
            }
        }
    }
}