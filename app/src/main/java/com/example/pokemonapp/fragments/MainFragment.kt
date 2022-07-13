package com.example.pokemonapp.activities.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.models.PokemonsModels

class MainFragment : Fragment() {
    private lateinit var adapter : MyAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var pokemonArrayList : ArrayList<PokemonsModels>

    lateinit var imageid : Array<Int>
    lateinit var infoPokemon : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyAdapter(pokemonArrayList)
        recyclerView.adapter = adapter

    }

   private fun dataInitialize(){

       pokemonArrayList = arrayListOf<PokemonsModels>()

       imageid = arrayOf(
           R.drawable.eevee22,
           R.drawable.flareon,
           R.drawable.jolteon,
           R.drawable.vaporeon,
           R.drawable.espeon,
           R.drawable.umbreon,
           R.drawable.sylveon,
           R.drawable.glaceon,
           R.drawable.leafeon
       )

       infoPokemon = arrayOf(
           getString(R.string.eevee22),
           getString(R.string.flareon),
           getString(R.string.jolteon),
           getString(R.string.vaporeon),
           getString(R.string.espeon),
           getString(R.string.umbreon),
           getString(R.string.sylveon),
           getString(R.string.glaceon),
           getString(R.string.leafeon),

       )

       for (i in imageid.indices){

           val pokemons = PokemonsModels(imageid[i],infoPokemon[i])
           pokemonArrayList.add(pokemons)
       }

   }
}