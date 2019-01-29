package com.coryperkins.pokedex

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

class PokemonAdapter(val pokemonData: List<PokemonSpecies>) : RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {
    class PokemonViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val textView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.pokemon_view, parent, false) as TextView

        return PokemonViewHolder(textView)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {

        holder.textView.text = pokemonData.get(position).name
    }

    override fun getItemCount() = pokemonData.size
}