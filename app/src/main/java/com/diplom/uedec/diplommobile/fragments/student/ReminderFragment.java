package com.diplom.uedec.diplommobile.fragments.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diplom.uedec.diplommobile.R;

/**
 * Created by uedec on 10.05.2019.
 */

public class ReminderFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.remainder_fragment, container, false);
        return view;
    }
}
