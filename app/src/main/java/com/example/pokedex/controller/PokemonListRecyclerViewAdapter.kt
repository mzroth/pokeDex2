package com.example.pokedex.controller

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pokedex.R
import com.example.pokedex.model.NameUrl

class PokemonListRecyclerViewAdapter(
    private var list: List<NameUrl>, private val clickListener:
    PokemonListRecyclerViewClickListener
): RecyclerView.Adapter<PokemonListRecyclerViewHolder>() {

    //    interface for the listener
    interface PokemonListRecyclerViewClickListener {
        fun pokemonClicked(number: Int)
    }

    // inflates layout from predefined layout
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PokemonListRecyclerViewHolder {
        return PokemonListRecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pokemon_list_recycler_view_holder,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    //    binds views and set's the clickListners
    override fun onBindViewHolder(holder: PokemonListRecyclerViewHolder, position: Int) {
        holder.pokemonListIdView.text = (position + 1).toString()
        holder.pokemonListNameView.text = list[position].name?.capitalize()
        holder.itemView.setOnClickListener { clickListener.pokemonClicked(position + 1) }
    }

    //    allows the recycler view to be set up and then updated once the asynchronous API request returns.
    fun updateList(list: List<NameUrl>) {
        this.list = list
        notifyDataSetChanged()
    }
}