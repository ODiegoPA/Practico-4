package com.example.practicaapipersonas.repositories

import android.util.Log
import com.example.practicaapipersonas.api.APILibreria
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.models.Libros
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BooksRepository {
    fun getLibrosList(success: (Libros?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apilibreria.jmacboy.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: APILibreria = retrofit.create(APILibreria::class.java)
        service.getLibros().enqueue(object : Callback<Libros> {
            override fun onResponse(res: Call<Libros>, response: Response<Libros>) {
                if (response.isSuccessful) {
                    val postList = response.body()
                    Log.d("API Response", "Success: ${postList?.size} items received.")
                    success(postList)
                } else {
                    Log.e("API Response", "Failed with status code: ${response.code()}")
                    failure(Exception("Failed with status code: ${response.code()}"))
                }
            }
            override fun onFailure(res: Call<Libros>, t: Throwable) {
                Log.e("API Response", "Error: ${t.message}")
                failure(t)
            }
        })
    }
    fun getLibro(id: Int, success: (Libro?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreria =
            retrofit.create(APILibreria::class.java)

        service.getLibroById(id).enqueue(object : Callback<Libro?> {
            override fun onResponse(res: Call<Libro?>, response: Response<Libro?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Libro?>, t: Throwable) {
                failure(t)
            }
        })
    }
    fun insertLibro(
        libro: Libro,
        success: (Libro) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreria =
            retrofit.create(APILibreria::class.java)
        service.insertLibro(libro).enqueue(object : Callback<Libro> {
            override fun onResponse(res: Call<Libro>, response: Response<Libro>) {
                val objLibro = response.body()
                Log.d("LibroInsertado", libro.toString())
                success(objLibro!!)
            }

            override fun onFailure(res: Call<Libro>, t: Throwable) {
                failure(t)
                Log.d("LibronoInsertado", libro.toString())
            }
        })
    }

    fun updateLibro(
        libro: Libro,
        id: Int,
        success: (Libro) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreria =
            retrofit.create(APILibreria::class.java)

        service.updateLibro(libro, id).enqueue(object : Callback<Libro> {
            override fun onResponse(res: Call<Libro>, response: Response<Libro>) {
                val objLibro = response.body()
                Log.d("LibroActualizado", libro.toString())
                success(objLibro!!)
            }

            override fun onFailure(res: Call<Libro>, t: Throwable) {
                failure(t)
                Log.d("LibroNoActualizado", libro.toString())
            }
        })
    }

    fun deleteLibro(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreria =
            retrofit.create(APILibreria::class.java)

        service.deleteLibro(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }

}
