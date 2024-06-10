package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicaapipersonas.databinding.GeneroItemLayoutBinding
import com.example.practicaapipersonas.models.Genero
import com.example.practicaapipersonas.models.Generos

class GeneroAdapter(val GeneroList: Generos, val listener: OnGeneroClickListener) :
    RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val binding =
            GeneroItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return GeneroViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return GeneroList.size
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val genero = GeneroList[position]
        holder.bind(genero, listener)
    }


    fun updateData(GeneroList: Generos) {
        this.GeneroList.clear()
        this.GeneroList.addAll(GeneroList)
        notifyDataSetChanged()
    }

    class GeneroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genero: Genero, listener: OnGeneroClickListener) {
            val binding = GeneroItemLayoutBinding.bind(itemView)
            binding.apply {
                lblGeneroNombre.text = "${genero.nombre}"
            }
        }
    }

    interface OnGeneroClickListener {
        fun onGeneroEdit(genero: Genero)
        fun onGeneroDelete(genero: Genero)
        fun onGeneroClick(genero: Genero)

    }

}