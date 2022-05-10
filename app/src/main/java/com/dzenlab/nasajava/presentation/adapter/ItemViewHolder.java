package com.dzenlab.nasajava.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.dzenlab.nasajava.R;
import com.dzenlab.nasajava.models.ItemNet;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardView;

    private final TextView titleTV;

    private final TextView dateTV;


    public static ItemViewHolder create(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_item, parent, false);

        return new ItemViewHolder(view);
    }

    private ItemViewHolder(View itemView) {

        super(itemView);

        cardView = itemView.findViewById(R.id.card_view);

        titleTV = itemView.findViewById(R.id.title_text_view);

        dateTV = itemView.findViewById(R.id.date_text_view);
    }

    public void bind(@Nullable ItemNet itemNet, ItemAdapter.ClickCallback clickCallback) {

        if(itemNet != null) {

            titleTV.setText(itemNet.getTitle());

            String date = new SimpleDateFormat("dd MMMM yyyy", new Locale("ru"))
                    .format(itemNet.getDate());

            dateTV.setText(date);

            cardView.setOnClickListener(view -> clickCallback.onClickListener(itemNet.getUrl()));
        }
    }
}