package com.example.pokedex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemonList = Pokemon.getPokemonFromJSON("pokemon.json", this)

        val adapter = PokemonAdapter(this, pokemonList)
        pokemon_list_view.adapter = adapter

        pokemon_list_view.setOnItemClickListener { _, _, position, _ ->
            val selectedPokemon = adapter.getItem(position) as Pokemon

            val detailsIntent = PokemonDetailActivity.newIntent(this@MainActivity, selectedPokemon)
            startActivity(detailsIntent)
        }
    }
}