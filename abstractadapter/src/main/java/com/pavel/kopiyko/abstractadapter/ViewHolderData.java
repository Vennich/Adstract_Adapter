package com.pavel.kopiyko.abstractadapter;

public class ViewHolderData {
    public final int viewType;
    public final Object data;
    public final String key;

    public ViewHolderData(int viewType, Object data, String key) {
        this.viewType = viewType;
        this.data = data;
        this.key = key;
    }
}