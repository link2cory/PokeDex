package com.coryperkins.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.os.StrictMode


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val pokemonData: List<PokemonSpecies>

    init {
        // todo: put this in a thread you dummy!
        // bypass the networkOnMainThreadException
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val api = PokeAPI()

        // get the total number of pokemon
        val speciesCountResponse = api.getSpeciesCount().execute()

        // if the request failed, set the count to zero
        val speciesCount = speciesCountResponse.body()?.count ?: 0

        // get the actual pokemon data
        val speciesListResponse = api.getSpeciesList(speciesCount).execute()

        // if the request failed set the data to empty
        pokemonData = speciesListResponse.body()?.results ?: emptyList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()
    }


    private fun setupRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = PokemonAdapter(pokemonData)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            // setHasFixedSize()
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }
}
