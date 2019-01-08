package com.about.country.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.about.country.R;
import com.about.country.model.AboutCountryListItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ksundara on 1/7/2019.
 */

public class CountryRecycleAdapter extends RecyclerView.Adapter<CountryRecycleAdapter.ViewHolder> {

    private Context context;
    private List<AboutCountryListItem> rows;

    public CountryRecycleAdapter(Context context, List<AboutCountryListItem> rows) {
        this.context = context;
        this.rows = rows;
    }

    public CountryRecycleAdapter(Context context) {
        this.context = context;
    }

    public void addListOfRows(List<AboutCountryListItem> rows) {
        this.rows = rows;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //  holder.itemView.setTag(rows.get(position));

        AboutCountryListItem row = rows.get(position);

        if (row.getTitle() != null) {
            holder.itemName.setText(row.getTitle());
        } else {
            holder.itemName.setText(R.string.no_title_found);
        }
        if (row.getDescription() != null) {
            holder.itemDesc.setText(row.getDescription());
        } else {
            holder.itemDesc.setText(R.string.no_description_found);
        }
        if (row.getImageHref() != null) {
         //   Log.d("URLLLL", "" + row.getTitle() + row.getImageHref());
            Picasso.with(context).load(row.getImageHref())
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(holder.itemImg);
        } else {
            Picasso.with(context).load(R.drawable.placeholder_image)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .into(holder.itemImg);

        }


    }

    @Override
    public int getItemCount() {
        return rows.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView itemName;
        @BindView(R.id.description)
        public TextView itemDesc;
        @BindView(R.id.item_img)
        public ImageView itemImg;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
