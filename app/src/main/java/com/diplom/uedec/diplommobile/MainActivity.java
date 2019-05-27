package com.diplom.uedec.diplommobile;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.AppDatabase;
import com.diplom.uedec.diplommobile.fragments.LoginFragment;
import com.diplom.uedec.diplommobile.fragments.RegisterFragment;


public class MainActivity extends AppCompatActivity {


    public void registerFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new RegisterFragment()).commit();
    }
    public void loginFragments() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new LoginFragment()).commit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }
}
