package com.example.pokedex.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {
//    Get for list of all pokemon.
    @GET("pokemon")
    fun getAllPokemon(@Query("limit") limit: String): Call<AllPokemon>

//    get for getting the information of a single pokemon when it is clicked
    @GET("pokemon/{id}")
    fun getSinglePokemon(@Path("id") id: String): Call<Pokemon>

//    convenient place to create() the pokeApi because i call it from different activities
    companion object {
        fun create(): PokeApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pokeapi.co/api/v2/")
                .build()
            return retrofit.create(PokeApi::class.java)
        }
    }
}