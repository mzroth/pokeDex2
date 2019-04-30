package com.example.pokedex.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.pokedex.R
import com.example.pokedex.model.AllPokemon
import com.example.pokedex.model.NameUrl
import com.example.pokedex.model.PokeApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

private const val POKEMON_TOTAL_NUMBER = "807"

class MainActivity: AppCompatActivity(),
    PokemonListRecyclerViewAdapter.PokemonListRecyclerViewClickListener {

    //    variables
    private var pokeService = PokeApi.create()
    private var list = listOf<NameUrl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        initialize RecyclerView
        pokemon_list_recycler_view.let {
            it.adapter = PokemonListRecyclerViewAdapter(list, this)
            it.layoutManager = LinearLayoutManager(this)
        }

//        asynchronous API call
        pokeService.getAllPokemon(POKEMON_TOTAL_NUMBER).enqueue(object: Callback<AllPokemon> {
            override fun onResponse(call: Call<AllPokemon>, response: Response<AllPokemon>) {
                response.body()?.results.let {
                    if (it != null) {
                        list = it
                    } else {
                        onFailure(call, IllegalStateException())
                    }
                }
                val recyclerViewAdapter = pokemon_list_recycler_view.adapter as? PokemonListRecyclerViewAdapter
                recyclerViewAdapter?.updateList(list)
            }

            override fun onFailure(call: Call<AllPokemon>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Please close the app and try again", Toast.LENGTH_LONG).show()
            }
        })
    }


    //    split into its own function in case added utility is needed later
    override fun pokemonClicked(number: Int) {
        startActivity(Intent(this, PokemonDetail::class.java).putExtra(PokemonDetail.INTENT_POKEMON_KEY, number))
    }
}
