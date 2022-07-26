package com.example.pokemonapp.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.models.PokemonsModels
import com.example.pokemonapp.databinding.FragmentMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter : MyAdapter
    private lateinit var pokemonArrayList : ArrayList<PokemonsModels>
    private lateinit var newPokemonArrayList : ArrayList<PokemonsModels>

    lateinit var imageid : Array<Int>
    lateinit var infoPokemon : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                newPokemonArrayList.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()){

                    pokemonArrayList.forEach{

                        if (it.infoPokemon.lowercase(Locale.getDefault()).contains(searchText)){


                            newPokemonArrayList.add(it)
                        }

                    }

                    binding.recycler.adapter!!.notifyDataSetChanged()

                }else{

                    newPokemonArrayList.clear()
                    newPokemonArrayList.addAll(pokemonArrayList)
                    binding.recycler.adapter!!.notifyDataSetChanged()

                }


                return false

            }

        })

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        //recyclerView = view.findViewById(R.id.recycler)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.setHasFixedSize(true)
        adapter = MyAdapter(newPokemonArrayList)
        binding.recycler.adapter = adapter

    }

   private fun dataInitialize(){

       pokemonArrayList = arrayListOf<PokemonsModels>() //lista de los nombre
       newPokemonArrayList = arrayListOf<PokemonsModels>() //lista de los nombre

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
           //lista de los textos
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

       newPokemonArrayList.addAll(pokemonArrayList)

   }
}