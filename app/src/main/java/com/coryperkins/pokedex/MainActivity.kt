package com.coryperkins.pokedex

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val pokemonData: ArrayList<String>

    init {
        pokemonData = arrayListOf("Bulbasaur", "IvySaur", "Venosaur")
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
