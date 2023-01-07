package com.example.pokemonapp.activities.fragments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.models.PokemonModel
import com.squareup.picasso.Picasso

class MyAdapter(var context: Context,private val newsList : List<PokemonModel>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
        parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsList[position]
        val currentItemImageUrl : String = currentItem.img.toString()
        val replaceHttp = currentItemImageUrl.replace("http","https")

        holder.bind(currentItem,replaceHttp)

    }

    override fun getItemCount(): Int {
        return newsList.size
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {

        val imagePokemon : ImageView = itemView.findViewById(R.id.imagePokemon)
        val txtInfoPokemon : TextView = itemView.findViewById(R.id.infoPokemon)

        fun bind(currentItem: PokemonModel, replaceHttp: String){

            Picasso.get() //con esto setearemos la imagen
                .load(replaceHttp)
                .placeholder(R.drawable.eevee)
                .resize(250, 300)
                .into(imagePokemon)

            /* Glide
                 .with(context)
                 .load("http://www.serebii.net/pokemongo/pokemon/001.png")
                 //.centerCrop()
                 //.placeholder(R.drawable.eevee)
                 .into(holder.imagePokemon)*/

            //holder.imagePokemon.setImageResource(currentItem.img)
            txtInfoPokemon.text = currentItem.name //con esto setearemos el nombre

        }

    }

}