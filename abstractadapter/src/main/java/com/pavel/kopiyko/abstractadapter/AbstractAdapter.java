package com.pavel.kopiyko.abstractadapter;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class AbstractAdapter {
    private final SparseArray<HolderFactory> factories;

    private AbstractAdapter(SparseArray<HolderFactory> factories) {
        this.factories = factories;
    }



    public static class Builder {
        private SparseArray<HolderFactory> factories;

        Builder() {
            factories = new SparseArray<>(3);
        }

        Builder(int initialCapacity) {
            factories = new SparseArray<>(initialCapacity);
        }

        public Builder setFactory(HolderFactory factory) {
            factories.put(factory.getLayoutId(), factory);
            return this;
        }

        public AbstractAdapter build() {
            if (factories.size() == 0) {
                throw new NoSuchElementException("Can't create adapter without ViewHolder factories." +
                        "\nuse addFactory() Builder's method");
            }

            return new AbstractAdapter(factories);
        }
    }
}
