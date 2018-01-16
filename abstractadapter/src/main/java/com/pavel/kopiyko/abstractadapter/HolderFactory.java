package com.pavel.kopiyko.abstractadapter;

import android.view.ViewGroup;

public interface HolderFactory {

    public int getLayoutId();

    public AbstractViewHolder createViewHolder(ViewGroup parent);
}
