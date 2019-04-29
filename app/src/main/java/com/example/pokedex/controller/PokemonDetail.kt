package com.example.pokedex.controller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.model.PokeApi
import com.example.pokedex.model.PokeType
import com.example.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//Conversion factor for height and weight that are measures in decameters and decagrams
private const val conversionFactor = 10

class PokemonDetail : AppCompatActivity() {

//    Variables
    private val somethingWrongUrl = "https://i.redd.it/2urjp7yzl1z01.png"
    private val noImageUrl = "https://upload.wikimedia.org/wikipedia/commons/f/fc/No_picture_available.png"
    private var pokemonNumber = 0
    lateinit var pokeService: PokeApi
    private var pokemon: Pokemon? = null
    private var pokemonDisplayNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        pokemonNumber = intent.getIntExtra(MainActivity.INTENT_POKEMON_KEY, 1)
        pokemonDisplayNumber = pokemonNumber.toString()

        pokeService = PokeApi.create()

//        Making the asynchronous request to the API
        pokeService.getSinglePokemon(pokemonNumber.toString()).enqueue(object: Callback<Pokemon> {
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                pokemon = response.body()
                if(pokemon == null) {
                    onFailure(call, IllegalStateException())
                }
                setDisplay(pokemon?.sprites?.frontDefault ?: noImageUrl)
            }

//            loads a something went wrong image for the user to know to try again and to facilitate in troubleshooting
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                setImage(somethingWrongUrl)
            }
        })
    }

//   This sets the second activities display with the corresponding pokemon attributes
    private fun setDisplay(image: String) {
        setImage(image)
        setHeight()
        setWeight()
        pokemonDisplaySetter()
        setTypeViews(pokemon?.types ?: throw IllegalStateException())
    }

//   This sets an image into the image view.
    private fun setImage(image: String) {
        Glide.with(this).load(image).into(poke_image_view)
    }

//    This sets the action bar with the pokemon's name and id#. The ID is converted into the standard three digit number
//    that is used normally in the game, but doesn't work for the API
    private fun pokemonDisplaySetter() {
        when {
            pokemonNumber < 10 -> pokemonDisplayNumber = " #00$pokemonDisplayNumber"
            pokemonNumber < 100 -> pokemonDisplayNumber = " #0$pokemonDisplayNumber"
            else -> pokemonDisplayNumber = " #$pokemonDisplayNumber"
        }
        supportActionBar?.title = pokemon?.name?.capitalize() + pokemonDisplayNumber
    }

//    Converts height to meters from decameters
    private fun setHeight() {
        height_text_view.text = getString(R.string.height, ((pokemon?.height ?: 0).toFloat()/conversionFactor).toString())
    }

//    Converts weight to kilograms from decagrams
    private fun setWeight() {
        weight_text_view.text = getString(R.string.weight, ((pokemon?.weight ?: 0).toFloat()/conversionFactor).toString())
    }

//    This set's the pokemon's types in the correct order.
    private fun setTypeViews(types: List<PokeType>?) {
        val primaryType = types?.firstOrNull {it.slot == 1}
        val secondaryType = types?.firstOrNull {it.slot == 2}
        type1_text_view.text = getString(R.string.type1, primaryType?.type?.name?.capitalize())
        if (secondaryType != null) {
            type2_text_view.background = getDrawable(R.drawable.text_view_background)
            type2_text_view.text = getString(R.string.type2, secondaryType.type?.name?.capitalize())
        }
    }
}
