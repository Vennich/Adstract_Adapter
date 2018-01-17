package com.pavel.kopiyko.abstractadapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

class AdapterDataHelper {

    private List<ViewHolderData> data;

    AdapterDataHelper() {
        data = new ArrayList<>();
    }


    int size() {
        return data.size();
    }

    void setData(List<ViewHolderData> dataList) {
        int posFrom = this.data.size();
        this.data.addAll(dataList);
    }

    boolean isEmpty() {
        return data.isEmpty();
    }

    void clear() {
        data.clear();
    }


    void addAll(Collection<ViewHolderData> data) {
        this.data.addAll(data);
    }

    void add(ViewHolderData item) {
        data.add(item);
    }


    ViewHolderData get(int position) {
        return data.get(position);
    }

    ViewHolderData get(String key) {
        for (ViewHolderData item : data) {
            if (item.key.equals(key)) {
                return item;
            }
        }

        return null;
    }


    void set(int position, ViewHolderData item) {
        if (!isContainsPosition(position)) {
            throw new IndexOutOfBoundsException("Can't update item on position " + position +
                    " for data size " + data.size() + ".");
        }

        data.set(position, item);
    }


    int removeItem(String key) {
        int posItemToRemove = 0;

        for (ViewHolderData item : data) {
            if (item.key.equals(key)) break;
            else posItemToRemove++;
        }
        if (!isContainsPosition(posItemToRemove)) {
            throw new NoSuchElementException("Not contains item with key " + key);
        }


        data.remove(posItemToRemove);
        return posItemToRemove;
    }

    void removeItem(int position) {
        if (!isContainsPosition(position)) {
            throw new IndexOutOfBoundsException("Can't remove item on position " + position +
                    " for data size " + data.size() + ".");
        }

        data.remove(position);
    }

    //helpers
    private boolean isContainsPosition(int position) {
        return position < data.size();
    }
}
