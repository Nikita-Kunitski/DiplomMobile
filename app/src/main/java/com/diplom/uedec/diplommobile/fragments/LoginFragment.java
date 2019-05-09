package com.diplom.uedec.diplommobile.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.diplom.uedec.diplommobile.HomeActivity;
import com.diplom.uedec.diplommobile.MainActivity;
import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.retrofit.REST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by uedec on 08.05.2019.
 */

public class LoginFragment extends Fragment {

    TextInputEditText email,password;
    Button login, register;
    ProgressBar progressBar;

    public void authentication(String email,String password){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(getResources().getString(R.string.BASE_URL)).build();
        REST REST =retrofit.create(REST.class);
        Call<Void> call= REST.Auth(email,password);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("responce-message",response.raw().message());
                Log.i("responce-headers",response.headers().toString());
                Log.i("responce-Set-Cookie",response.headers().get("Set-Cookie")==null ? "null":response.headers().get("Set-Cookie"));
                Log.i("responce-headers",response.raw().message().equals("Bad Request")? "lox" : "success");
                if(response.raw().message().equals("OK")){
                    // TODO сделать лоадер
                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                }
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                login.setEnabled(true);
                register.setEnabled(true);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("responce",t.toString());
                Log.i("responce-headers","LOX");
                Toast.makeText(getActivity(),"Проблемы с сетью. Попробуйте еще раз",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                login.setEnabled(true);
                register.setEnabled(true);
            }
        });
        progressBar.setVisibility(ProgressBar.VISIBLE);
        login.setEnabled(false);
        register.setEnabled(false);


    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        login = view.findViewById(R.id.log_in);
        register = view.findViewById(R.id.register);
        progressBar=view.findViewById(R.id.progressBar);
        //сделать проверку полей
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authentication(email.getText().toString(),password.getText().toString());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).registerFragments();
            }
        });
        return view;
    }
}