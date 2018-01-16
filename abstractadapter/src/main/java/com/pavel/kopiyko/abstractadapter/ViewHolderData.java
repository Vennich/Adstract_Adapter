package com.pavel.kopiyko.abstractadapter;

public class ViewHolderData {
    final int viewType;
    final Object data;
    final String key;

    public ViewHolderData(int viewType, Object data, String key) {
        this.viewType = viewType;
        this.data = data;
        this.key = key;
    }
}