package com.michi.filmmaster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

data class ListView(val mCtx : Context, val resource : Int, val items : List<FilmView>) : ArrayAdapter<FilmView>(mCtx,resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)

        val film : FilmView = items[position]

        imageView.setImageDrawable(mCtx.resources.getDrawable(film.image))
        textView.text = film.name

        return view
    }
}

