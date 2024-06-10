package com.example.practicaapipersonas.models
typealias Libros = ArrayList<Libro>
data class Libro (
    val nombre: String,
    val autor: String,
    val editorial: String,
    val imagen: String,
    val sinopsis: String,
    val isbn: String,
    val calificacion: Int
) {
    val generos: List<Genero> = emptyList()
    var id: Int? = null
}