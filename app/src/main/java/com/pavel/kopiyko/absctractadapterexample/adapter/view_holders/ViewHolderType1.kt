package com.pavel.kopiyko.absctractadapterexample.adapter.view_holders

import android.view.View
import com.pavel.kopiyko.absctractadapterexample.RemoveItemListener
import com.pavel.kopiyko.absctractadapterexample.adapter.factories.ViewHolderTwoFactory
import com.pavel.kopiyko.abstractadapter.AbstractViewHolder
import com.pavel.kopiyko.abstractadapter.ViewHolderData

class ViewHolderType1(itemView: View,
                      private val onRemoveItemListener: RemoveItemListener)
    : AbstractViewHolder(itemView), View.OnClickListener {

    private lateinit var data : String

    init {
        itemView.setOnClickListener(this)
    }

    override fun setData(position: Int, data: ViewHolderData) {
        this.data = ViewHolderTwoFactory.convertToData(data)
    }

    override fun onClick(p0: View) {
        onRemoveItemListener.onRemove(adapterPosition)
        //remove click listener, 'cause while animation running
        //can tap on it and it will cause of Exception or wrong behavior
        itemView.setOnClickListener(null)
    }
}