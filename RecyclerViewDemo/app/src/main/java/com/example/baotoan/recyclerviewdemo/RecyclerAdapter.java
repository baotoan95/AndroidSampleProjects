package com.example.baotoan.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BaoToan on 11/14/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataViewHolder> {
    private Context context;
    private List<Person> persons;

    public RecyclerAdapter(Context context, List<Person> persons) {
        this.context = context;
        this.persons = persons;
    }

    @Override
    public RecyclerAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;

        switch (viewType) {
            case 1:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_name, parent, false);
                break;
            case 2:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_name_female, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(context).inflate(R.layout.item_name, parent, false);
        }
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {
        holder.txtName.setText(persons.get(position).getName());
    }

    @Override
    public int getItemViewType(int position) {
        if (persons.get(position).isMale()) {
            return 1;
        }
        return 2;
    }

    @Override
    public int getItemCount() {
        return persons == null ? 0 : persons.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;

        public DataViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txt_name);
        }
    }
}
