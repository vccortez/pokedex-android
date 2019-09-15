package com.example.pokedex

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class PokemonAdapter(private val context: Context, private val dataSource: ArrayList<Pokemon>) :
    BaseAdapter() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    companion object {
        private val LABEL_COLORS = hashMapOf(
            "folha" to R.color.colorFolha,
            "fogo" to R.color.colorFogo,
            "água" to R.color.colorAgua,
            "elétrico" to R.color.colorEletrico,
            "normal" to R.color.colorNormal,
            "venenoso" to R.color.colorVeneno,
            "psíquico" to R.color.colorPsiquico,
            "fada" to R.color.colorFada
        )
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        // 1
        if (convertView == null) {

            // 2
            view = inflater.inflate(R.layout.pokemon_list_item, parent, false)

            // 3
            holder = ViewHolder()
            holder.thumbnailImageView = view.findViewById(R.id.pokemon_item_thumbnail) as ImageView
            holder.titleTextView = view.findViewById(R.id.pokemon_item_title) as TextView
            holder.numberTextView = view.findViewById(R.id.pokemon_item_number) as TextView
            holder.subtitleTextView = view.findViewById(R.id.pokemon_item_subtitle) as TextView
            holder.detail1TextView = view.findViewById(R.id.pokemon_item_detail1) as TextView
            holder.detail2TextView = view.findViewById(R.id.pokemon_item_detail2) as TextView

            // 4
            view.tag = holder
        } else {
            // 5
            view = convertView
            holder = convertView.tag as ViewHolder
        }

        // 6
        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val numberTextView = holder.numberTextView
        val detail1TextView = holder.detail1TextView
        val detail2TextView = holder.detail2TextView
        val thumbnailImageView = holder.thumbnailImageView

        val pokemon = getItem(position) as Pokemon

        titleTextView.text = pokemon.name
        numberTextView.text = pokemon.number.toString()
        subtitleTextView.text = pokemon.entry
        detail1TextView.text = pokemon.type[0]
        detail2TextView.text = pokemon.type.getOrElse(1) { "" }

        Picasso.with(context).load(pokemon.imageUrl).placeholder(R.mipmap.ic_launcher)
            .into(thumbnailImageView)

        detail1TextView.setTextColor(
            ContextCompat.getColor(
                context,
                LABEL_COLORS[pokemon.type[0]] ?: R.color.colorNormal
            )
        )

        if (pokemon.type.size > 1) {
            detail2TextView.setTextColor(
                ContextCompat.getColor(
                    context,
                    LABEL_COLORS[pokemon.type[1]] ?: R.color.colorNormal
                )
            )
        }

        return view
    }

    override fun getItem(position: Int): Any = dataSource[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = dataSource.size

    private class ViewHolder {
        lateinit var titleTextView: TextView
        lateinit var numberTextView: TextView
        lateinit var subtitleTextView: TextView
        lateinit var detail1TextView: TextView
        lateinit var detail2TextView: TextView
        lateinit var thumbnailImageView: ImageView
    }
}