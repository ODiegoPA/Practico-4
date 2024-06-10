package com.example.practicaapipersonas.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.models.Libros
import com.example.practicaapipersonas.repositories.BooksRepository
import com.example.practicaapipersonas.ui.adapters.LibroAdapter

class MainViewModel:ViewModel (){
    private val _booksList: MutableLiveData<Libros> by lazy {
        MutableLiveData<Libros>(Libros())
    }
    val categoryList: LiveData<Libros> get() = _booksList
    fun fetchListaPersonas() {
        BooksRepository.getLibrosList(
            success = { libros ->
                libros?.let {
                    _booksList.value = it
                }
            },
            failure = {
                it.printStackTrace()
                Log.d("Error", "No se pudo obtener los datos")
            }
        )
    }
}