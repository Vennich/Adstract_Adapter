package com.pavel.kopiyko.abstractadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AbstractViewHolder extends RecyclerView.ViewHolder {
    public AbstractViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setData(ViewHolderData data);
}
