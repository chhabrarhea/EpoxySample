package com.rhea.epoxy.viewholders

import android.view.ViewGroup
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.textview.MaterialTextView
import com.rhea.epoxy.R
import com.rhea.epoxy.epoxy.Holder
import okio.blackholeSink

@EpoxyModelClass(layout = R.layout.title_view)
abstract class TitleModel(val totalNumber: Int) :
    EpoxyModelWithHolder<TitleModel.TitleHolder>() {

    override fun bind(holder: TitleHolder) {
        val lp = holder.card.layoutParams as ViewGroup.MarginLayoutParams
        val appearanceModel: ShapeAppearanceModel.Builder =
            if (totalNumber != 0){
                holder.heading.text = "$totalNumber Movies Waiting for you!"
                lp.bottomMargin = 0
                ShapeAppearanceModel().toBuilder()
                    .setTopLeftCorner(CornerFamily.ROUNDED, 48F)
                    .setTopRightCorner(CornerFamily.ROUNDED, 48F)
            }
        else{
                lp.bottomMargin = 16
                holder.heading.text = ""
                ShapeAppearanceModel().toBuilder()
                    .setBottomLeftCorner(CornerFamily.ROUNDED, 48F)
                    .setBottomRightCorner(CornerFamily.ROUNDED, 48F)
            }
        holder.card.shapeAppearanceModel = appearanceModel.build()
    }

    inner class TitleHolder : Holder() {
        val card by bind<MaterialCardView>(R.id.title_card)
        val heading by bind<MaterialTextView>(R.id.heading_tv)
    }
}