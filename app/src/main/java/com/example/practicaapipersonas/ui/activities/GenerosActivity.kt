package com.example.practicaapipersonas.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicaapipersonas.R
import com.example.practicaapipersonas.databinding.ActivityGenerosBinding
import com.example.practicaapipersonas.databinding.ActivityMainBinding
import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.models.Generos
import com.example.practicaapipersonas.ui.adapters.GeneroAdapter
import com.example.practicaapipersonas.ui.adapters.LibroAdapter
import com.example.practicaapipersonas.ui.viewmodels.GenerosViewModel

class GenerosActivity : AppCompatActivity(), GeneroAdapter.OnGeneroClickListener {
    lateinit var binding: ActivityGenerosBinding
    private val model: GenerosViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGenerosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupViewModelListeners()
        setupRecyclerView()
    }
    override fun onResume() {
        super.onResume()
        model.fetchListaGeneros()
    }
    private fun setupViewModelListeners() {
        model.generosList.observe(this) {
            val adapter = (binding.generosList.adapter as GeneroAdapter)
            adapter.updateData(it)
        }
    }
    private fun setupRecyclerView() {
        binding.generosList.apply {
            Log.d("Texto","No")
            this.adapter = GeneroAdapter(Generos(), this@GenerosActivity)
            layoutManager = LinearLayoutManager(this@GenerosActivity)
        }
    }

    override fun onGeneroEdit(genero: Genero) {
        TODO("Not yet implemented")
    }

    override fun onGeneroDelete(genero: Genero) {
        TODO("Not yet implemented")
    }

    override fun onGeneroClick(genero: Genero) {
        TODO("Not yet implemented")
    }
}