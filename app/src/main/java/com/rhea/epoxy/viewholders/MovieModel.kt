package com.rhea.epoxy.viewholders

import android.widget.TextView
import coil.load
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.imageview.ShapeableImageView
import com.rhea.epoxy.R
import com.rhea.epoxy.epoxy.Holder
import com.rhea.epoxy.models.Movie

@EpoxyModelClass(layout = R.layout.content_view)
abstract class MovieModel(@EpoxyAttribute var model: Movie) :
    EpoxyModelWithHolder<MovieModel.MovieHolder>() {

    override fun bind(holder: MovieHolder) {
        holder.apply {
            val context = nameTextView.context
            nameTextView.text = model.title
            idTextView.text = model.overview
            if (model.poster_path != null){
                posterImageView.load("https://image.tmdb.org/t/p/w185/${model.poster_path}")
            }
        }
    }


    inner class MovieHolder : Holder() {
        val posterImageView by bind<ShapeableImageView>(R.id.contact_img)
        val idTextView by bind<TextView>(R.id.contact_number)
        val nameTextView by bind<TextView>(R.id.contact_name)
    }
}