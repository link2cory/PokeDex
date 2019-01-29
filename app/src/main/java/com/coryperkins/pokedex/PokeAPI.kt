package com.coryperkins.pokedex

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonSpeciesDataService {
    @GET("pokemon-species")
    fun getPokemonSpeciesList(@Query("limit")limit: Int): Call<PokemonSpeciesResponse>

    @GET("pokemon-species")
    fun getPokemonSpeciesCount(): Call<PokemonSpeciesCountResponse>
}

class PokemonSpecies(val name: String, val url: String)
class PokemonSpeciesResponse(val results: List<PokemonSpecies>)
class PokemonSpeciesCountResponse(val count: Int)

class PokeAPI {
    private val pokemonSpecies: PokemonSpeciesDataService

    init {
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        pokemonSpecies = retrofit.create(PokemonSpeciesDataService::class.java)
    }

    fun getSpeciesList(limit: Int) : Call<PokemonSpeciesResponse> {
        return pokemonSpecies.getPokemonSpeciesList(limit)
    }

    fun getSpeciesCount() : Call<PokemonSpeciesCountResponse> {
        return pokemonSpecies.getPokemonSpeciesCount()
    }
}