package com.confidence.btit95.confidencesecretbeta.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.confidence.btit95.confidencesecretbeta.EditDiaryActivity;
import com.confidence.btit95.confidencesecretbeta.R;
import com.confidence.btit95.confidencesecretbeta.entities.DiaryEntity;

import java.util.List;

/**
 * Created by baotoan on 01/07/2017.
 */

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {
    private List<DiaryEntity> diaries;
    private Context context;

    public DiaryAdapter(Context context, List<DiaryEntity> diaries) {
        this.context = context;
        this.diaries = diaries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        DiaryEntity diary = diaries.get(position);
        holder.title.setText(diary.getTitle());
        holder.desc.setText(diary.getDesc());
        holder.date.setText(diary.getDate().toString());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditDiaryActivity.class);
                intent.putExtra("diary", diaries.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return diaries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public int currentItem;
        public TextView title;
        public TextView desc;
        public TextView date;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.item_title);
            desc = (TextView) itemView.findViewById(R.id.item_desc);
            date = (TextView) itemView.findViewById(R.id.item_date);
        }
    }
}
