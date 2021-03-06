package com.pavel.kopiyko.absctractadapterexample.adapter.view_holders

import android.view.View
import com.pavel.kopiyko.absctractadapterexample.RemoveItemListener
import com.pavel.kopiyko.absctractadapterexample.adapter.factories.ViewHolderOneFactory
import com.pavel.kopiyko.abstractadapter.AbstractViewHolder
import com.pavel.kopiyko.abstractadapter.ViewHolderData

class ViewHolderType2(itemView: View,
                      private val onRemoveItemListener: RemoveItemListener)
    : AbstractViewHolder(itemView), View.OnClickListener {

    private lateinit var data : String

    init {
        itemView.setOnClickListener(this)
    }

    override fun setData(position: Int, data: ViewHolderData) {
        this.data = ViewHolderOneFactory.convertToData(data)
    }

    override fun onClick(p0: View) {
        if (adapterPosition == -1) {
            //animation running and we are trying don't need to remove already removing view
            //it will cause of Exception or wrong behavior
        } else {
            onRemoveItemListener.onRemove(adapterPosition)
        }
    }
}