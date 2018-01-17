package com.pavel.kopiyko.abstractadapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.List;
import java.util.NoSuchElementException;

public class AbstractAdapter extends RecyclerView.Adapter<AbstractViewHolder> {

    private final SparseArray<HolderFactory> factories;
    private AdapterDataHelper data;


    public void setData(List<ViewHolderData> dataList) {
        int posFrom = this.data.size();
        this.data.addAll(dataList);

        notifyItemRangeInserted(posFrom, dataList.size());
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        notifyItemRangeRemoved(0, data.size());
        data.clear();
    }

    public void add(ViewHolderData item) {
        data.add(item);
        notifyItemInserted(data.size());
    }

    public void add(int position, ViewHolderData item) {
        data.add(position, item);

        notifyItemInserted(position);
    }

    public ViewHolderData get(int position) {
        return data.get(position);
    }

    public ViewHolderData get(String key) {
        return data.get(key);
    }

    public void set(int position, ViewHolderData item) {
        data.set(position, item);
        notifyItemChanged(position);
    }

    public void removeItem(String key) {
        notifyItemRemoved(data.removeItem(key));
    }

    public void removeItem(int position) {
        data.removeItem(position);
        notifyItemRemoved(position);
    }



    //adapter's overrides
    @Override
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return factories.get(viewType).createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(AbstractViewHolder holder, int position) {
        holder.setData(position, data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).viewType;
    }






    //create
    private AbstractAdapter(SparseArray<HolderFactory> factories) {
        this.factories = factories;
        data = new AdapterDataHelper();
    }

    public static class Builder {
        private SparseArray<HolderFactory> factories;

        public Builder() {
            factories = new SparseArray<>(3);
        }

        public Builder(int initialCapacity) {
            factories = new SparseArray<>(initialCapacity);
        }

        public Builder setFactory(HolderFactory factory) {
            factories.put(factory.getLayoutId(), factory);
            return this;
        }

        public AbstractAdapter build() {
            if (factories.size() == 0) {
                throw new NoSuchElementException("Can't create adapter without ViewHolder's factories." +
                        "\nuse addFactory() Builder's method");
            }

            return new AbstractAdapter(factories);
        }
    }
}
