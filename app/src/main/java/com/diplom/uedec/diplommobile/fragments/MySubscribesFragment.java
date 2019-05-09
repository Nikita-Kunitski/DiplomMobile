package com.diplom.uedec.diplommobile.fragments;

import android.arch.persistence.room.Embedded;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.data.entity.ApplicationUser;
import com.diplom.uedec.diplommobile.data.entity.Auditorium;
import com.diplom.uedec.diplommobile.data.entity.Event;

/**
 * Created by uedec on 08.05.2019.
 */

public class MySubscribesFragment extends Fragment {



    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.mysubscribes_fragment, container, false);
        return view;
    }
}
