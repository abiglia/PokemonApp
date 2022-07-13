package com.example.pokemonapp.activities.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.models.PokemonsModels

class MyAdapter(private val newsList : ArrayList<PokemonsModels>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        holder.imagePokemon.setImageResource(currentItem.titleimage)
        holder.txtInfoPokemon.text = currentItem.infoPokemon
    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        val imagePokemon : ImageView = itemView.findViewById(R.id.imagePokemon)
        val txtInfoPokemon : TextView = itemView.findViewById(R.id.infoPokemon)

    }

}