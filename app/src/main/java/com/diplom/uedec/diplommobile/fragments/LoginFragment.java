package com.diplom.uedec.diplommobile.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.diplom.uedec.diplommobile.MainActivity;
import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.retrofit.REST;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginFragment extends Fragment {

    TextInputEditText email,password;
    Button login, register;

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

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("responce",t.toString());
                Log.i("responce-headers","LOX");
            }
        });
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
