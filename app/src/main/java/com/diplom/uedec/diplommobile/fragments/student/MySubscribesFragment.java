package com.diplom.uedec.diplommobile.fragments.student;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplom.uedec.diplommobile.DetailSubscribeAndDeleteActivity;
import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.RecyclerViewAdapters.DataEventsAdapter;
import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.AppDatabase;
import com.diplom.uedec.diplommobile.data.dao.ApplicationUserDao;
import com.diplom.uedec.diplommobile.data.dao.AuditoriumDao;
import com.diplom.uedec.diplommobile.data.dao.EventDao;
import com.diplom.uedec.diplommobile.data.dao.LessonDao;
import com.diplom.uedec.diplommobile.data.dao.StudentEventDao;
import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.Event;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.data.entity.StudentEvent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by uedec on 08.05.2019.
 */

public class MySubscribesFragment extends Fragment implements DataEventsAdapter.onEventListner {

    @Override
    public void onEventClick(int position) {
        Log.i("test",String.valueOf(position));
        Intent intent=new Intent(getActivity(), DetailSubscribeAndDeleteActivity.class);
        EventWithAllMembers item=m_eventWithAllMembers.get(position);
        intent.putExtra("EventWithAllMembers",item);
        startActivity(intent);
    }

    class getMySubscribes extends AsyncTask<Void,Void,List<EventWithAllMembers>>
    {
        @Override
        protected void onPostExecute(List<EventWithAllMembers> eventWithAllMembers) {
            m_eventWithAllMembers=eventWithAllMembers;
            if(m_eventWithAllMembers.size()==0)
            {
                recyclerView.setVisibility(RecyclerView.INVISIBLE);
                message.setVisibility(TextView.VISIBLE);
            }
            SetAdapter(m_eventWithAllMembers);
        }

        @Override
        protected List<EventWithAllMembers> doInBackground(Void... voids) {
            AppDatabase db=App.getInstance().getDatabase();
            EventDao eventDao=db.eventDao();
            ApplicationUserDao applicationUserDao=db.applicationUserDao();
            LessonDao lessonDao=db.lessonDao();
            AuditoriumDao auditoriumDao=db.auditoriumDao();
            StudentEventDao studentEventDao=db.studentEventDao();
            List<StudentEvent> studentEventList=studentEventDao.getByStudentId(App.user.getId());
            List<EventWithAllMembers>listEvents=new ArrayList<>();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
            DateFormat df2=new SimpleDateFormat("HH:mm");
            for (StudentEvent item: studentEventList
                 ) {
                try {
                Event event=eventDao.getById(item.getEventId());
                Lesson lesson=lessonDao.getById(event.getLessonId());
                ApplicationUser teacher= applicationUserDao.getById(event.getTeacherId());
                Auditorium auditorium=auditoriumDao.getById(event.getAuditoriumId());

                    Date date=df.parse(event.getDate());
                    Date startTime=df2.parse(event.getStartTime());
                    Date endTime=df2.parse(event.getEndTime());
                    listEvents.add(new EventWithAllMembers(event.getId(),
                                                        date,
                                                        startTime,
                                                        endTime,
                                                        event.getCountPeople(),
                                                        event.getLessonId(),
                                                        event.getEventName(),
                                                        event.getAuditoriumId(),
                                                        event.getTeacherId(),
                                                        auditorium,
                                                        teacher,
                                                        lesson));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return listEvents;

        }
    }

    public void SetAdapter(List<EventWithAllMembers> mresult)
    {
        DataEventsAdapter adapter = new DataEventsAdapter(getContext(), mresult, this);
        recyclerView.setAdapter(adapter);
    }

    TextView message;
    List<EventWithAllMembers> m_eventWithAllMembers;
    RecyclerView recyclerView;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.mysubscribes_fragment, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.mySubscribesList);
        message = view.findViewById(R.id.message_);
        new getMySubscribes().execute();

        return view;
    }
}
