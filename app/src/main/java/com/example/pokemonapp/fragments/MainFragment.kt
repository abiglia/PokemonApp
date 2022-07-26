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
import com.example.pokemonapp.services.ApiServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var adapter : MyAdapter
    private lateinit var recyclerView : RecyclerView
    private lateinit var pokemonArrayList : ArrayList<PokemonsModels>
    private lateinit var searchView: SearchView

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

        getRetrofit()

        callService()


        return binding.root

    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun callService() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(ApiServices::class.java).getPokemon()
            val pokemon = call.body()
            activity?.runOnUiThread {
                if (call.isSuccessful) {
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
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.setHasFixedSize(true)
        adapter = MyAdapter(pokemonArrayList)
        binding.recycler.adapter = adapter

    }

   private fun dataInitialize(){

       pokemonArrayList = arrayListOf<PokemonsModels>() //lista de las imagenes

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

   }
}