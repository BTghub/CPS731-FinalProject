package com.example.databaseui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder> {

    private final LayoutInflater mInflater;
    public List<Course> mCourses; // Cached copy of Courses
    private final CourseListAdapter.ListItemClickListener mOnClickListener;

    CourseListAdapter(Context context, CourseListAdapter.ListItemClickListener onClickListener) {
        mInflater = LayoutInflater.from(context);
        mOnClickListener = onClickListener;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if (mCourses != null) {
            Course current = mCourses.get(position);
            holder.CourseItemView.setText(current.title);
        } else {
            // Covers the case of data not being ready yet.
            holder.CourseItemView.setText("No Courses");
        }
    }

    void setCourses(List<Course> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mCourses has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView CourseItemView;

        private CourseViewHolder(View itemView) {
            super(itemView);
            CourseItemView = itemView.findViewById(R.id.textView);
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
