package com.rhea.epoxy.paged

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.rhea.epoxy.models.Movie
import com.rhea.epoxy.models.RewardHeaderModel
import com.rhea.epoxy.viewholders.HeaderModel_
import com.rhea.epoxy.viewholders.MovieModel_
import com.rhea.epoxy.viewholders.TitleModel_

class PagedController : PagingDataEpoxyController<Movie>() {

    var headerData = RewardHeaderModel(0, 0)
    override fun buildItemModel(currentPosition: Int, item: Movie?): EpoxyModel<*> {
        return item?.let {
            MovieModel_(it)
                .id(it.id)
        } ?: TitleModel_(0)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        HeaderModel_(headerData)
            .id("header")
            .addTo(this)
        if (models.isNotEmpty())
            TitleModel_(models.size)
                .id("title")
                .addTo(this)
        super.addModels(models)
        if (models.isNotEmpty())
            TitleModel_(0)
                .id("footer")
                .addTo(this)
    }
}