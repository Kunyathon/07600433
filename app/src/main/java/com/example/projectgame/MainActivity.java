package com.example.projectgame;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectgame.adapter.UserAdapter;
import com.example.projectgame.db.AppDatabase;
import com.example.projectgame.model.User;
import com.example.projectgame.util.AppExecutors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private RecyclerView mRecycleView;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleView = findViewById(R.id.user_recycler_view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                User[] users = db.userDao().getAllUsers();

                UserAdapter adater = new UserAdapter(MainActivity.this, users);
                mRecycleView.setAdapter(adater);
                /*
                for (User u : users) {
                    Log.i(TAG, u.firstName + " " + u.lastName);
                    msg+= String.format{
                        Locale.getDefault(),
                    "%s %s %s\n",
                    u.firstName, u.lastName, DataFormatter.formatForUi(u.birthDate)

                    };
                }

                final String message = msg;
                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        *//*new AlertDialog.Builder(MainActivity.this)
                                .setMessage(message)
                                .setPositiveButton("OK" ,null)
                                .show();*//*
                    }
                });*/
            }
        });
        Button addButton = findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, activity_and_user.class);
                startActivity(i);
            }
        });

    }
}
