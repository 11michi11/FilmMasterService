package com.michi.filmmaster

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

data class ListView(val mCtx : Context, val resource : Int, val items : List<Film>) : ArrayAdapter<Film>(mCtx,resource, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)
        val view: View = layoutInflater.inflate(resource, null)

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)

        val film : Film = items[position]

        Glide.with(context).load(film.posterURL).into(imageView).onLoadFailed()

        textView.text = film.title

        return view
    }


}

