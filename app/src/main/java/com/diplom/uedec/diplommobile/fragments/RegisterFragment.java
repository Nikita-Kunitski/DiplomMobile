package com.diplom.uedec.diplommobile.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.diplom.uedec.diplommobile.MainActivity;
import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.retrofit.REST;
import com.diplom.uedec.diplommobile.retrofit.RequestRegisterStudent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RegisterFragment extends Fragment {
    Button back,register;
    TextInputEditText firstName, lastName, patronymic, group, course, studentNumber,email,password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.register_fragment, container, false);
        back = view.findViewById(R.id.back);
        register=view.findViewById(R.id.register);
        firstName=view.findViewById(R.id.first_name);
        lastName=view.findViewById(R.id.last_name);
        patronymic = view.findViewById(R.id.patronymic);
        group=view.findViewById(R.id.group);
        course=view.findViewById(R.id.course);
        studentNumber = view.findViewById(R.id.studentNumber);
        email=view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).loginFragments();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestRegisterStudent requestRegisterStudent=new RequestRegisterStudent();
                requestRegisterStudent.setEmail(email.getText().toString());
                requestRegisterStudent.setPassword(password.getText().toString());
                Retrofit retrofit=new Retrofit.Builder().baseUrl(getResources().getString(R.string.BASE_URL)).addConverterFactory(GsonConverterFactory.create()).
                        build();
                REST REST =retrofit.create(REST.class);
                Call<Void> call= REST.Register(requestRegisterStudent);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.i("responce-message",response.raw().message());
                        Log.i("responce-headers",response.headers().toString());
                        Log.i("responce-Set-Cookie",response.headers().get("Set-Cookie")==null ? "null":response.headers().get("Set-Cookie"));
                        Log.i("responce-headers",response.raw().message().equals("Bad Request")? "lox" : "success");
                        if(response.raw().message().equals("OK")) {
                            Toast.makeText(getActivity(),"Регистрация прошла успешно",Toast.LENGTH_SHORT).show();
                            ((MainActivity) getActivity()).loginFragments();//
                        }
                        else
                            Toast.makeText(getActivity(),"Пользователь с таким email уже существует",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.i("responce",t.toString());
                        Log.i("responce-headers","LOX");
                    }
                });
            }
        });
        return view;
    }
}
