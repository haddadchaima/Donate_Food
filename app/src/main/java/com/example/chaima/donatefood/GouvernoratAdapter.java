package com.example.chaima.donatefood;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GouvernoratAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<Gouvernorat> rcountriesArrayList = new ArrayList<>();
    Context context;
    OnItemClickListener onItemClickListener;

    public GouvernoratAdapter(ArrayList<Gouvernorat> countriesArrayList) {
        this.rcountriesArrayList = countriesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountriesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_gouvernorat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final CountriesViewHolder viewHolder = (CountriesViewHolder) holder;
        final Gouvernorat gouver = rcountriesArrayList.get(position);

            viewHolder.textViewName.setText(gouver.getGouver_Name());
            viewHolder.chkItemGouver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(gouver);
                }*/


            }
        });

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return rcountriesArrayList.size();
    }


    public interface OnItemClickListener {
        void onItemClick(Gouvernorat gouver);
    }

    class CountriesViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        CheckBox chkItemGouver;

       //LinearLayout linearLayoutContainer;

        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.text_gouver);
            chkItemGouver=itemView.findViewById(R.id.chkbox_gouver);
           // linearLayoutContainer=itemView.findViewById(R.id.linearLayout_container);
        }
    }
}
