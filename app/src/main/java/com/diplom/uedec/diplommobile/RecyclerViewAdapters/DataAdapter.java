package com.diplom.uedec.diplommobile.RecyclerViewAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.fragments.EventFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by uedec on 09.05.2019.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<EventFragment.EventWithAllMembers> _eventWithAllMembersList;
    public DataAdapter(Context context, List<EventFragment.EventWithAllMembers> eventWithAllMembersList) {
        this._eventWithAllMembersList = eventWithAllMembersList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        EventFragment.EventWithAllMembers eventWithAllMembers = _eventWithAllMembersList.get(position);
        holder.eventName.setText(eventWithAllMembers.eventName);
        holder.auditorium.setText(eventWithAllMembers.auditorium.getAuditoriumName());
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        DateFormat df2=new SimpleDateFormat("HH:mm:ss");
        holder.time.setText(df.format(eventWithAllMembers.date)+"\n"+df2.format(eventWithAllMembers.startTime)+" - "+df2.format(eventWithAllMembers.endTime));
        holder.teacher.setText(eventWithAllMembers.teacher.getEmail());
    }

    @Override
    public int getItemCount() {
        return _eventWithAllMembersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView eventName, auditorium, time, teacher;
        ViewHolder(View view){
            super(view);
            eventName = (TextView) view.findViewById(R.id.eventName);
            auditorium = (TextView) view.findViewById(R.id.auditorium);
            time = (TextView) view.findViewById(R.id.time);
            teacher=(TextView) view.findViewById(R.id.teacher);
        }
    }
}
