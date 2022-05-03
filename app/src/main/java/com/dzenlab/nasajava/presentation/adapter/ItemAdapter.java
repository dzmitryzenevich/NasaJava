package com.dzenlab.nasajava.presentation.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import com.dzenlab.nasajava.models.ItemNet;

public class ItemAdapter extends ListAdapter<ItemNet, ItemViewHolder> {

    private final ClickCallback clickCallback;

    public interface ClickCallback {

        void onClickListener(String url);
    }

    public static class ItemDiff extends DiffUtil.ItemCallback<ItemNet> {

        @Override
        public boolean areItemsTheSame(@NonNull ItemNet oldItemNet, @NonNull ItemNet newItemNet) {

            return oldItemNet.equals(newItemNet);
        }

        @Override
        public boolean areContentsTheSame(@NonNull ItemNet oldItemNet, @NonNull ItemNet newItemNet) {

            return oldItemNet.getId() == newItemNet.getId() &&
                    oldItemNet.getTitle().equals(newItemNet.getTitle()) &&
                    oldItemNet.getDate().equals(newItemNet.getDate()) &&
                    oldItemNet.getUrl().equals(newItemNet.getUrl());
        }
    }

    public ItemAdapter(ClickCallback clickCallback,
                       @NonNull DiffUtil.ItemCallback<ItemNet> diffCallback) {

        super(diffCallback);

        this.clickCallback = clickCallback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return ItemViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        holder.bind(getItem(position), clickCallback);
    }

    public long getItemId(int position) {

        return getCurrentList().get(position).getId();
    }
}
