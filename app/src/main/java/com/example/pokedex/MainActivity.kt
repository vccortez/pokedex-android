package com.example.pokedex

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokemon = Pokemon.getPokemonFromJSON("pokemon.json", this)

        for (p in pokemon) {
            Log.d("POKEMONS", p.toString())
        }
    }
}