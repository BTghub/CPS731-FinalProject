package com.example.databaseui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> {

    private final LayoutInflater mInflater;
    public List<Event> mEvents; // Cached copy of Events
    private final EventListAdapter.ListItemClickListener mOnClickListener;

    EventListAdapter(Context context, EventListAdapter.ListItemClickListener onClickListener) {
        mInflater = LayoutInflater.from(context);
        mOnClickListener = onClickListener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        if (mEvents != null) {
            Event current = mEvents.get(position);
            holder.EventItemView.setText(current.date + ": " + current.title);
        } else {
            // Covers the case of data not being ready yet.
            holder.EventItemView.setText("No Events");
        }
    }

    void setEvents(List<Event> events){
        mEvents = events;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mEvents has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEvents != null)
            return mEvents.size();
        else return 0;
    }

    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView EventItemView;

        private EventViewHolder(View itemView) {
            super(itemView);
            EventItemView = itemView.findViewById(R.id.textView);
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
