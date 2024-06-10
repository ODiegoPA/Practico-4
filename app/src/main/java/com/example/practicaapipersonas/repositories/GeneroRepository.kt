package com.example.practicaapipersonas.repositories

import android.util.Log
import com.example.practicaapipersonas.api.APILibreria
import com.example.practicaapipersonas.models.Generos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GeneroRepository {
    fun getGeneroList(success: (Generos?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreria =
            retrofit.create(APILibreria::class.java)

        service.getGeneros().enqueue(object : Callback<Generos> {
            override fun onResponse(res: Call<Generos>, response: Response<Generos>) {
                val postList = response.body()
                Log.d("CategoriasSuccess", postList.toString())
                success(postList)
            }

            override fun onFailure(res: Call<Generos>, t: Throwable) {
                Log.d("CategoriasFailure", t.message.toString())
                failure(t)
            }
        })
    }
}