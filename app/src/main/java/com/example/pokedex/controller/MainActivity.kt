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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.IllegalStateException

class MainActivity : AppCompatActivity(),
    PokemonListRecyclerViewAdapter.PokemonListRecyclerViewClickListener {

//    variables
    lateinit var pokemonListRecyclerView: RecyclerView
    lateinit var pokeService: PokeApi
    private var list: List<NameUrl> = listOf()

//    companion object for API request and intent keys for switching activities
    companion object {
        const val INTENT_POKEMON_KEY = "pokemonNumber"
        const val POKEMON_TOTAL_NUMBER = "807"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        initialize late int
        pokeService = PokeApi.create()
        pokemonListRecyclerView = findViewById(R.id.pokemon_list_recycler_view)
        pokemonListRecyclerView.adapter = PokemonListRecyclerViewAdapter(list, this)
        pokemonListRecyclerView.layoutManager = LinearLayoutManager(this)

//        asynchronous API call
        pokeService.getAllPokemon(POKEMON_TOTAL_NUMBER).enqueue(object: Callback<AllPokemon> {
            override fun onResponse(call: Call<AllPokemon>, response: Response<AllPokemon>) {
                list = response.body()?.results ?: throw IllegalStateException()
                val recyclerViewAdapter = pokemonListRecyclerView.adapter as? PokemonListRecyclerViewAdapter
                recyclerViewAdapter?.updateList(list)
            }

            override fun onFailure(call: Call<AllPokemon>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Please close the app and try again", Toast.LENGTH_LONG).show()
            }
        })
    }

//    split into its own function in case added utility is needed later
    override fun pokemonClicked(number: Int) {
        showPokemonDetail(number)
    }

//    makes the intent and switches to new activity
    private fun showPokemonDetail(number: Int) {
        val pokemonDetailIntent = Intent(this, PokemonDetail::class.java)
        pokemonDetailIntent.putExtra(INTENT_POKEMON_KEY, number)
        startActivity(pokemonDetailIntent)
    }
}
