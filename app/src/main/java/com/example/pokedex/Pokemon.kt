package com.example.pokedex

import android.content.Context
import org.json.JSONException
import org.json.JSONObject


data class Pokemon(
    val number: Int,
    val name: String,
    val species: String,
    val imageUrl: String,
    val url: String,
    val entry: String,
    val type: Array<String>
) {

    companion object {

        fun getPokemonFromJSON(filename: String, context: Context): ArrayList<Pokemon> {
            val pokemonList = ArrayList<Pokemon>()

            try {
                // carrega o JSON dentro de /assets
                val jsonString = loadJsonFromAssets(filename, context)
                val json = JSONObject(jsonString)
                val pokemon = json.getJSONArray("pokemon")

                // recupera objetos pokemon da array e adiciona na lista
                (0 until pokemon.length()).mapTo(pokemonList) {
                    val typeArray = pokemon.getJSONObject(it).getJSONArray("type")
                    Pokemon(pokemon.getJSONObject(it).getInt("number"),
                        pokemon.getJSONObject(it).getString("name"),
                        pokemon.getJSONObject(it).getString("species"),
                        pokemon.getJSONObject(it).getString("image"),
                        pokemon.getJSONObject(it).getString("url"),
                        pokemon.getJSONObject(it).getString("entry"),
                        Array(typeArray.length()) { t ->
                            typeArray.getString(t)
                        })
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            return pokemonList
        }

        private fun loadJsonFromAssets(filename: String, context: Context): String? {
            val json: String?

            try {
                val inputStream = context.assets.open(filename)
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                json = String(buffer, Charsets.UTF_8)
            } catch (ex: java.io.IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }
    }

    override fun toString(): String {
        return "Pokémon - $number: $name, $species ${type.contentToString()}"
    }
}