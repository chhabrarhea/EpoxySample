package com.rhea.epoxy.simple

import android.util.Log
import com.airbnb.epoxy.AutoModel
import com.airbnb.epoxy.Typed2EpoxyController
import com.rhea.epoxy.models.Movie
import com.rhea.epoxy.models.RewardHeaderModel
import com.rhea.epoxy.viewholders.*

class SimpleController: Typed2EpoxyController<List<Movie>, RewardHeaderModel>() {
    override fun buildModels(data1: List<Movie>?, data2: RewardHeaderModel?) {
        Log.i("observer fml", data2.toString() +" "+ data1?.size)
        if (data2 !=  null)
            HeaderModel_(data2)
                .id("header")
                .addTo(this)

        TitleModel_(data1?.size?: 20)
            .id("title")
            .addTo(this)

        data1?.forEach {
            MovieModel_(it)
                .id(it.id)
                .addTo(this)
        }

        TitleModel_(0)
            .id("footer")
            .addTo(this)
    }
}