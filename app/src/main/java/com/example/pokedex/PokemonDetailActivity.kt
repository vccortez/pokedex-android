package com.example.pokedex

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "name"
        const val EXTRA_URL = "url"

        fun newIntent(context: Context, pokemon: Pokemon): Intent {
            val detailIntent = Intent(context, PokemonDetailActivity::class.java)

            detailIntent.putExtra(EXTRA_NAME, pokemon.name)
            detailIntent.putExtra(EXTRA_URL, pokemon.url)

            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        val name = intent.extras.getString(EXTRA_NAME)
        val url = intent.extras.getString(EXTRA_URL)

        title = name

        detail_web_view.loadUrl(url)
    }
}
