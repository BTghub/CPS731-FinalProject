package com.example.databaseui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WorkListAdapter extends RecyclerView.Adapter<WorkListAdapter.WordViewHolder> {

    private final LayoutInflater mInflater;
    public List<Work> mWork; // Cached copy of words
    private final ListItemClickListener mOnClickListener;

    WorkListAdapter(Context context, ListItemClickListener onClickListener) {
        mInflater = LayoutInflater.from(context);
        mOnClickListener = onClickListener;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new WordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        if (mWork != null) {
            Work current = mWork.get(position);
            holder.wordItemView.setText(current.course + ": " + current.workType + " due: " + current.dueDate);
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Work");
        }
    }

    void setWork(List<Work> work){
        mWork = work;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mWork != null)
            return mWork.size();
        else return 0;
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView wordItemView;

        private WordViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(position);
        }
    }

    // Implement clickable items
    interface ListItemClickListener{
        void onListItemClick(int position);
    }
}
