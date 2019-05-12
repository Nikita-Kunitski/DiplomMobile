package com.diplom.uedec.diplommobile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diplom.uedec.diplommobile.MainActivity;
import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.data.App;

/**
 * Created by uedec on 12.05.2019.
 */

public class DetailUserTeacherFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_user_teacher_fragment, container, false);
        TextInputLayout textInputLayout=view.findViewById(R.id.textInputLayout);
        TextInputLayout textInputLayout2=view.findViewById(R.id.textInputLayout2);
        TextInputLayout textInputLayout3=view.findViewById(R.id.textInputLayout3);
        TextInputLayout textInputLayout4=view.findViewById(R.id.textInputLayout4);
        TextInputLayout textInputLayout5=view.findViewById(R.id.textInputLayout5);
        textInputLayout.setEnabled(false);
        textInputLayout2.setEnabled(false);
        textInputLayout3.setEnabled(false);
        textInputLayout4.setEnabled(false);
        textInputLayout5.setEnabled(false);

        TextInputEditText teacherNumber=view.findViewById(R.id.teacherNumber);
        teacherNumber.setText(/*App.user.getStudentNumber()*/"");
        TextInputEditText email=view.findViewById(R.id.email);
        email.setText(App.user.getEmail());
        TextInputEditText lastName=view.findViewById(R.id.last_name);
        lastName.setText(/*App.user.getLastName()*/"");
        TextInputEditText firstName=view.findViewById(R.id.first_name);
        firstName.setText(/*App.user.getFirstName()*/"");
        TextInputEditText patronymic=view.findViewById(R.id.patronymic);
        patronymic.setText(/*App.user.getPatronymic()*/"");
        Button button=view.findViewById(R.id.log_out);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
