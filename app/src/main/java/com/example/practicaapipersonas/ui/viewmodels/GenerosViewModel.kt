package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.models.Generos
import com.example.practicaapipersonas.repositories.GeneroRepository

class GenerosViewModel: ViewModel() {
    private val _genresList: MutableLiveData<Generos> by lazy {
        MutableLiveData<Generos>(Generos())
    }
    val generosList: LiveData<Generos> get() = _genresList
    fun fetchListaGeneros(){
        GeneroRepository.getGeneroList(
            success = { generos ->
                generos?.let{
                    _genresList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}