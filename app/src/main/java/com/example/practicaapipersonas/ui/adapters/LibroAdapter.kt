package com.example.practicaapipersonas.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.practicaapipersonas.databinding.LibroItemLayoutBinding
import com.example.practicaapipersonas.models.Libro
import com.example.practicaapipersonas.models.Libros

class LibroAdapter(val personaList: Libros, val listener: OnLibrosClickListener) :
    RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding =
            LibroItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return LibroViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return personaList.size
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val persona = personaList[position]
        holder.bind(persona, listener)
    }

    fun updateData(personaList: List<Libro>) {
        val librosArrayList = ArrayList(personaList.toMutableList())
        this.personaList.clear()
        this.personaList.addAll(librosArrayList.sortedByDescending { it.calificacion })
        notifyDataSetChanged()
    }

    class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(libro: Libro, listener: OnLibrosClickListener) {
            val binding = LibroItemLayoutBinding.bind(itemView)
            binding.apply {
                lblPersonaFullName.text = "${libro.nombre}"
                txtCalificacion.text="${libro.calificacion}"
                Glide.with(itemView.context)
                    .load(libro.imagen)
                    .override(300, 300)
                    .into(imgCover)
                root.setOnClickListener { listener.onLibrosClick(libro) }
            }
        }
    }
    interface OnLibrosClickListener{
        fun onLibrosClick(libro: Libro)
    }
}