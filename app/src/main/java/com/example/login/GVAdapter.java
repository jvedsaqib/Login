package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GVAdapter extends ArrayAdapter<GridSearch> {

    // constructor for our list view adapter.
    public GVAdapter(@NonNull Context context, ArrayList<GridSearch> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.card_item_search, parent, false);
        }

        GridSearch dataModal = getItem(position);

        TextView tvTitleGrid = listitemView.findViewById(R.id.tvTitleGrid);
        TextView tvPriceGrid = listitemView.findViewById(R.id.tvPriceGrid);
        ImageView gridImg = listitemView.findViewById(R.id.gridImg);

        tvTitleGrid.setText(dataModal.getTitle());

        Picasso.get().load(dataModal.getImgUrl()).into(gridImg);

        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Here I will send the user to -> : " + dataModal.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return listitemView;
    }
}
