package com.diplom.uedec.diplommobile.RecyclerViewAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;



public class DataEventsAdapter extends RecyclerView.Adapter<DataEventsAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<EventWithAllMembers> _eventWithAllMembersList;

    private onEventListner monEventListner;

    public DataEventsAdapter(Context context, List<EventWithAllMembers> eventWithAllMembersList, onEventListner onEventListner) {
        this._eventWithAllMembersList = eventWithAllMembersList;
        this.inflater = LayoutInflater.from(context);
        this.monEventListner=onEventListner;
    }

    @Override
    public DataEventsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_events_item, parent, false);
        return new ViewHolder(view,monEventListner);
    }

    @Override
    public void onBindViewHolder(DataEventsAdapter.ViewHolder holder, int position) {
        EventWithAllMembers eventWithAllMembers = _eventWithAllMembersList.get(position);
        holder.eventName.setText(eventWithAllMembers.eventName);
        holder.auditorium.setText("Аудитория: "+eventWithAllMembers.auditorium.getAuditoriumName());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2=new SimpleDateFormat("HH:mm");
        holder.time.setText(df.format(eventWithAllMembers.date)+"\n"+df2.format(eventWithAllMembers.startTime)+" - "+df2.format(eventWithAllMembers.endTime));
        holder.teacher.setText("Преподаватель: "+eventWithAllMembers.teacher.getEmail());
    }

    @Override
    public int getItemCount() {
        return _eventWithAllMembersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView eventName, auditorium, time, teacher;
        onEventListner _onEventListner;

       public ViewHolder(@NonNull View view, onEventListner onEventListner){
            super(view);
            eventName = (TextView) view.findViewById(R.id.eventName);
            auditorium = (TextView) view.findViewById(R.id.auditorium);
            time = (TextView) view.findViewById(R.id.time);
            teacher=(TextView) view.findViewById(R.id.teacher);
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
