package com.example.pokedex.controller

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.pokedex.R

class PokemonListRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//    view holder that references pokemon_list_recycler_view_holder.xml
    val pokemonListIdView: TextView = view.findViewById(R.id.pokemon_list_id_text_view)
    val pokemonListNameView: TextView = view.findViewById(R.id.pokemon_list_name_text_view)

}
