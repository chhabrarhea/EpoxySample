package com.rhea.epoxy.epoxy

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

open class Holder : EpoxyHolder() {

    private lateinit var view: View

    override fun bindView(itemView: View) {
        view = itemView
    }

    protected fun <V : View> bind(id: Int): ReadOnlyProperty<Holder, V> =
        Lazy { holder: Holder, prop ->
            holder.view.findViewById(id) as V?
                ?: throw IllegalStateException("View ID $id for '${prop.name}' not found.")
        }


    /**
     * https://github.com/JakeWharton/kotterknife
     */
    private class Lazy<V>(private val initializer: (Holder, KProperty<*>) -> V)
        : ReadOnlyProperty<Holder, V> {

        private object EMPTY

        private var value: Any? = EMPTY

        override fun getValue(thisRef: Holder, property: KProperty<*>): V {
            if (value == EMPTY) {
                value = initializer(thisRef, property)
            }
            @Suppress("UNCHECKED_CAST")
            return value as V
        }
    }
}