package com.pavel.kopiyko.absctractadapterexample.adapter.factories

import android.view.LayoutInflater
import android.view.ViewGroup
import com.pavel.kopiyko.absctractadapterexample.R
import com.pavel.kopiyko.absctractadapterexample.RemoveItemListener
import com.pavel.kopiyko.absctractadapterexample.adapter.view_holders.ViewHolderType2
import com.pavel.kopiyko.abstractadapter.AbstractViewHolder
import com.pavel.kopiyko.abstractadapter.HolderFactory
import com.pavel.kopiyko.abstractadapter.ViewHolderData

class ViewHolderTwoFactory(private val onRemoveItemListener: RemoveItemListener) : HolderFactory {

    override fun getLayoutId(): Int = VIEW_TYPE

    override fun createViewHolder(parent: ViewGroup): AbstractViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolderType2(view, onRemoveItemListener)
    }

    fun createHolderData(textToItem: String, uniqueKey : String): ViewHolderData {
        return ViewHolderData(VIEW_TYPE, textToItem, uniqueKey)
    }

    companion object {
        val VIEW_TYPE = R.layout.item_type_2

        fun convertToData(viewHolderData: ViewHolderData) : String {
            return viewHolderData.data as String
        }
    }
}