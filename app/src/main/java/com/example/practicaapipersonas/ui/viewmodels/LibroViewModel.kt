package com.example.practicaapipersonas.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.repositories.BooksRepository

class LibroViewModel: ViewModel() {
    private val _libro: MutableLiveData<Libro?> by lazy {
        MutableLiveData<Libro?>(null)
    }
    val libro: LiveData<Libro?> get() = _libro

    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val closeActivity: LiveData<Boolean> get() = _closeActivity

    fun loadCategory(id: Int) {
        BooksRepository.getLibro(id,
            success = {
                _libro.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }

    fun deleteLibro(id: Int) {
        BooksRepository.deleteLibro(
            id,
            success = {
                _closeActivity.value = true
            },
            failure = {
                it.printStackTrace()
            })
    }
}