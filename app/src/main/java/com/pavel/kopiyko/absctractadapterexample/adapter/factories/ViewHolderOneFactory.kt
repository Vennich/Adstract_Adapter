package com.pavel.kopiyko.absctractadapterexample.adapter.factories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pavel.kopiyko.absctractadapterexample.R
import com.pavel.kopiyko.absctractadapterexample.RemoveItemListener
import com.pavel.kopiyko.absctractadapterexample.adapter.view_holders.ViewHolderType1
import com.pavel.kopiyko.abstractadapter.AbstractViewHolder
import com.pavel.kopiyko.abstractadapter.HolderFactory
import com.pavel.kopiyko.abstractadapter.ViewHolderData

class ViewHolderOneFactory(private val onRemoveItemListener: RemoveItemListener) : HolderFactory {

    override fun getLayoutId(): Int = VIEW_TYPE

    override fun createViewHolder(parent: ViewGroup): AbstractViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)

        return ViewHolderType1(view, onRemoveItemListener)
    }

    fun createHolderData(textToItem: String, uniqueKey : String): ViewHolderData {
        return ViewHolderData(VIEW_TYPE, textToItem, uniqueKey)
    }

    companion object {
        val VIEW_TYPE = R.layout.item_type_1

        fun convertToData(viewHolderData: ViewHolderData) : String {
            return viewHolderData.data as String
        }
    }
}