package com.diplom.uedec.diplommobile.RecyclerViewAdapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.data.entity.Lesson;

import java.util.List;

public class DataLessonsAdapter extends RecyclerView.Adapter<DataLessonsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Lesson> _lessons;

    private onEventListner monEventListner;

    public DataLessonsAdapter(Context context, List<Lesson> lessons, onEventListner onEventListner)
    {
        this._lessons=lessons;
        this.inflater=LayoutInflater.from(context);
        this.monEventListner=onEventListner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_lessons_item, parent, false);
        return new ViewHolder(view,monEventListner);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Lesson lesson=_lessons.get(position);
        holder.abbreviation.setText(lesson.getAbbreviation());
        holder.lessonName.setText(lesson.getName());
    }

    @Override
    public int getItemCount() {
        return _lessons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView lessonName, abbreviation;
        onEventListner _onEventListner;

        public ViewHolder(@NonNull View view, onEventListner onEventListner){
            super(view);
            lessonName = (TextView) view.findViewById(R.id.lessonName);
            abbreviation = (TextView) view.findViewById(R.id.abbreviation);
            this._onEventListner=onEventListner;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            _onEventListner.onEventClick(getAdapterPosition());
        }
    }

    public interface onEventListner {
        void onEventClick(int position);
    }
}
