package com.example.pokemonapp.activities.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.activities.models.PokemonsModels
import com.example.pokemonapp.databinding.FragmentMainBinding
import com.example.pokemonapp.models.PokemonModel
import com.example.pokemonapp.services.ApiServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter: MyAdapter
    private lateinit var pokemonArrayList: ArrayList<PokemonModel>
    private var newPokemonArrayList = mutableListOf<PokemonModel>()

    lateinit var imageid: Array<Int>
    lateinit var infoPokemon: Array<String>

    private val listCharacter = mutableListOf<PokemonModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun callService() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiServices::class.java).getPokemon()
            val pokemonCall = call.body()
            activity?.runOnUiThread {
                if (call.isSuccessful) {
                    val characterPokemon = pokemonCall?.pokemon ?: emptyList()
                    listCharacter.clear()
                    listCharacter.addAll(characterPokemon)
                    newPokemonArrayList.addAll(characterPokemon)
                    adapter.notifyDataSetChanged()
                    //val images: List<String> = puppies?.image ?: emptyList()
                    //dogImages.clear()
                    //dogImages.addAll(images)
                    //adapter.notifyDataSetChanged()
                } else {
                    //showError()
                }
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        initRecyclerView()

        //recyclerView = view.findViewById(R.id.recycler)

        // adapter = MyAdapter(newPokemonArrayList)
        getRetrofit()
        callService()
        searchView()


    }

    private fun searchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                listCharacter.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                if (searchText.isNotEmpty()) {

                    newPokemonArrayList.forEach {

                        if (it.name?.lowercase(Locale.getDefault())?.contains(searchText)!!) {

                            listCharacter.add(it)
                        }

                    }

                    binding.recycler.adapter!!.notifyDataSetChanged()

                } else {

                    listCharacter.clear()
                    listCharacter.addAll(newPokemonArrayList)
                    binding.recycler.adapter!!.notifyDataSetChanged()

                }


                return false

            }

        })
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        adapter = MyAdapter(requireContext(),listCharacter)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.setHasFixedSize(true)
        binding.recycler.adapter = adapter
    }

    private fun dataInitialize() {

        //pokemonArrayList = arrayListOf() //lista de los nombre
        //newPokemonArrayList = mutableListOf<PokemonModel> //lista de los nombre

       /* imageid = arrayOf(
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

        for (i in imageid.indices) {

            val pokemons = PokemonsModels(imageid[i], infoPokemon[i])
            pokemonArrayList.add(pokemons)
        }

        newPokemonArrayList.addAll(pokemonArrayList)*/

    }
}