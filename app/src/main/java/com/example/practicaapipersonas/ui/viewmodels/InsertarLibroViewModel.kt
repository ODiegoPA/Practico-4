package com.example.practicaapipersonas.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.repositories.BooksRepository

class InsertarLibroViewModel: ViewModel() {
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    private val _libro: MutableLiveData<Libro?> by lazy {
        MutableLiveData<Libro?>(null)
    }
    val libro: LiveData<Libro?> get() = _libro
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    fun loadLibro(id: Int){
        BooksRepository.getLibro(id,
            success = {
                _libro.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }
    fun saveLibro(
        id: Int,
        nombre: String,
        autor: String,
        editorial: String,
        ISBN: String,
        imagen : String,
        sinopsis : String,
        calificacion: Int
    ){
        val libro = Libro(
            nombre = nombre,
            autor = autor,
            editorial = editorial,
            isbn = ISBN,
            imagen = imagen,
            sinopsis = sinopsis,
            calificacion = calificacion
        )
        if (id != -1){
            Log.d("LibroSaveViewModel", "saveCategory: $id")

            libro.id = 0
            BooksRepository.updateLibro(
                libro,
                id,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        } else {
            BooksRepository.insertLibro(
                libro,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }
    fun updateLibro(

    ){

    }
}