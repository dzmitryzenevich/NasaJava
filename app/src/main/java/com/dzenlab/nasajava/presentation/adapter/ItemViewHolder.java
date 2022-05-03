package com.dzenlab.nasajava.presentation.adapter;

import static com.dzenlab.nasajava.presentation.constants.Constants.FORMAT_DATE;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

    public void bind(ItemNet itemNet, ItemAdapter.ClickCallback clickCallback) {

        titleTV.setText(itemNet.getTitle());

        String date = new SimpleDateFormat(FORMAT_DATE, new Locale("ru")).format(itemNet.getDate());

        dateTV.setText(date);

        cardView.setOnClickListener(view -> clickCallback.onClickListener(itemNet.getUrl()));
    }
}