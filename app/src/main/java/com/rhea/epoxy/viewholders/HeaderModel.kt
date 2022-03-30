package com.rhea.epoxy.viewholders

import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.*
import com.google.android.material.textview.MaterialTextView
import com.rhea.epoxy.R
import com.rhea.epoxy.epoxy.Holder
import com.rhea.epoxy.models.RewardHeaderModel

@EpoxyModelClass(layout = R.layout.header_view)
abstract class HeaderModel(@EpoxyAttribute var headerModel: RewardHeaderModel) :
    EpoxyModelWithHolder<HeaderModel.HeaderHolder>() {
    override fun bind(holder: HeaderHolder) {
        holder.apply {
            if (!pendingTab.isSelected || !earnedTab.isSelected)
                earnedTab.isSelected = true

            earnedTab.setOnClickListener {
                earnedTab.isSelected = true
                showAmount(holder)
            }

            pendingTab.setOnClickListener {
                pendingTab.isSelected = true
                showAmount(holder)
            }
        }

    }

    private fun showAmount(holder: HeaderHolder) {
        holder.apply {
            val showPending = pendingTab.isSelected
            val amount =
                if (showPending) headerModel.pendingAmount.toFloat() / 100
                else headerModel.earnedAmount.toFloat() / 100

            amountTextView.isActivated = amount.toString() == "0"
            amountTextView.isSelected = !showPending
            amountTextView.text = amount.toString()
        }
    }

    inner class HeaderHolder : Holder() {
        val amountTextView by bind<MaterialTextView>(R.id.amount_tv)
        val earnedTab by bind<MaterialTextView>(R.id.earned_card)
        val pendingTab by bind<MaterialTextView>(R.id.pending_card)
    }
}


