package com.dzenlab.nasajava.presentation.adapter;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import com.dzenlab.nasajava.models.ItemNet;

public class ItemAdapter extends PagingDataAdapter<ItemNet, ItemViewHolder> {

    private final ClickCallback clickCallback;

    public interface ClickCallback {

        void onClickListener(String url);
    }

    public static class ItemDiff extends DiffUtil.ItemCallback<ItemNet> {

        @Override
        public boolean areItemsTheSame(@NonNull ItemNet oldItemNet, @NonNull ItemNet newItemNet) {

            return oldItemNet.getId() == newItemNet.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ItemNet oldItemNet, @NonNull ItemNet newItemNet) {

            return oldItemNet.equals(newItemNet);
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

    @Nullable
    public ItemNet getItemNet(int position) {

        return getItem(position);
    }
}
