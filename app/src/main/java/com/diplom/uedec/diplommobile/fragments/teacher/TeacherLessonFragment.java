package com.diplom.uedec.diplommobile.fragments.teacher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.diplom.uedec.diplommobile.CreateEventActivity;
import com.diplom.uedec.diplommobile.HomeActivity;
import com.diplom.uedec.diplommobile.R;
import com.diplom.uedec.diplommobile.RecyclerViewAdapters.DataEventsAdapter;
import com.diplom.uedec.diplommobile.RecyclerViewAdapters.DataLessonsAdapter;
import com.diplom.uedec.diplommobile.TeacherHomeActivity;
import com.diplom.uedec.diplommobile.data.App;
import com.diplom.uedec.diplommobile.data.entity.EventWithAllMembers;
import com.diplom.uedec.diplommobile.data.entity.Lesson;
import com.diplom.uedec.diplommobile.data.entity.TeacherLesson;
import com.diplom.uedec.diplommobile.retrofit.REST;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by uedec on 14.05.2019.
 */

public class TeacherLessonFragment extends Fragment implements DataLessonsAdapter.onEventListner {


    @Override
    public void onEventClick(final int position) {
        AlertDialog.Builder ad;
        ad = new AlertDialog.Builder(getActivity());
        ad.setTitle("Удаление");
        ad.setMessage("Вы действительно хотите удалить этот предмет из своего списка?");
        ad.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sPref=getActivity().getSharedPreferences(App.APP_PREFERENCES,MODE_PRIVATE);
                final String token=sPref.getString(App.TOKEN,"");
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30,TimeUnit.SECONDS)
                        .addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request newRequest  = chain.request().newBuilder()
                                        .addHeader("Authorization", "Bearer " + token)
                                        .build();
                                return chain.proceed(newRequest);
                            }
                        })
                        .build();

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(getResources().getString(R.string.BASE_URL))
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

                final REST REST =retrofit.create(REST.class);
                TeacherLesson teacherLesson=new TeacherLesson(App.user.getId(),result.get(position).getId());
                Call<Void> call=REST.Unsubscribe(teacherLesson);
                progressBar.setVisibility(View.VISIBLE);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code()==200)
                        {
                            Toast.makeText(getActivity(), "Удаление выполнено успешно", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            ((TeacherHomeActivity)getActivity()).lessonFragments();
                        }
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }
        });
        ad.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        ad.show();
    }

    public void SetAdapter(List<Lesson> mresult)
    {
        DataLessonsAdapter adapter = new DataLessonsAdapter(getContext(), mresult, this);
        recyclerView.setAdapter(adapter);
        progressBar.setVisibility(View.GONE);
    }

    FloatingActionButton fab;
    List<Lesson> result;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    int lessonId=-1;
    OkHttpClient client;
    Retrofit retrofit;
    REST REST;
    Call<List<Lesson>> call,call1;
    SharedPreferences sPref;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        sPref=getActivity().getSharedPreferences(App.APP_PREFERENCES,MODE_PRIVATE);
        final String token=sPref.getString(App.TOKEN,"");
        client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request newRequest  = chain.request().newBuilder()
                                .addHeader("Authorization", "Bearer " + token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        REST =retrofit.create(REST.class);
        call = REST.getLessons(App.user.getId());
        result=null;
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStop() {
        if(result==null)
            call.cancel();
        if(App.lessons==null)
            call1.cancel();
        super.onStop();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.teacher_lessons_fragment, container, false);
        App.lessons=null;
        fab  = (FloatingActionButton) view.findViewById(R.id.fab);
        progressBar=(ProgressBar) view.findViewById(R.id.progressBar4);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView = (RecyclerView)view.findViewById(R.id.list);

        call.enqueue(new Callback<List<Lesson>>() {
            @Override
            public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                Log.i("responce-message",response.raw().message());
                Log.i("responce-headers",response.headers().toString());
                Log.i("responce-Set-Cookie",response.headers().get("Set-Cookie")==null ? "null":response.headers().get("Set-Cookie"));
                Log.i("responce-headers",response.raw().message().equals("Bad Request")? "lox" : "success");
                result=response.body();
                SetAdapter(result);
            }

            @Override
            public void onFailure(Call<List<Lesson>> call, Throwable t) {
                Log.i("responce-headers",t.getMessage());
            }
        });

        call1 = REST.getAllLessons(App.user.getId());
        call1.enqueue(new Callback<List<Lesson>>() {
            @Override
            public void onResponse(Call<List<Lesson>> call, Response<List<Lesson>> response) {
                Log.i("responce-message",response.raw().message());
                Log.i("responce-headers",response.headers().toString());
                Log.i("responce-headers",response.raw().message().equals("Bad Request")? "lox" : "success");
                App.lessons=response.body();
            }

            @Override
            public void onFailure(Call<List<Lesson>> call, Throwable t) {
                Log.i("responce-message",t.getMessage());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(App.lessons.size()!=0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    final ArrayList<String> lessonString = new ArrayList<>();
                    for (Lesson item : App.lessons
                            ) {
                        lessonString.add(item.getAbbreviation());
                    }
                    final String[] arr = new String[App.lessons.size()];
                    lessonString.toArray(arr);
                    builder.setTitle("Добавление предмета")
                            .setCancelable(false)
                            .setNeutralButton("Назад", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    if (lessonId == -1) {
                                        Toast.makeText(getActivity(), "Вы не выбрали ни один предмет", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Lesson selectLesson = App.lessons.get(lessonId);
                                        TeacherLesson teacherLesson = new TeacherLesson(App.user.getId(), selectLesson.getId());
                                        Call<Void> call = REST.SubscribeToLesson(teacherLesson);
                                        dialogInterface.cancel();
                                        progressBar.setVisibility(View.VISIBLE);
                                        call.enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                if (response.code() == 200) {
                                                    Toast.makeText(getActivity(), "Подписка выполнена успешно", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                Log.i("responce-message", t.getMessage());
                                            }
                                        });
                                    }
                                }
                            })
                            .setSingleChoiceItems(arr, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    lessonId = i;
                                }
                            }).show();
                }
                else if(App.lessons.size()==0)
                    Toast.makeText(getActivity(), "Предметов для подписки нет", Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }


}
