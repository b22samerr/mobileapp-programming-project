package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=b22samer";
    private final String JSON_FILE = "soccerteams.json";
    private RecyclerView recyclerView;
    private ArrayList<FootballTeams> footballTeamsList;
    private RecyclerViewAdapter adapter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        footballTeamsList = new ArrayList<>();



        recyclerView = findViewById(R.id.recycler_view);

        adapter = new RecyclerViewAdapter(footballTeamsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
            }
        });

        //new JsonTask(this).execute(JSON_URL);
        new JsonFile(this,this).execute(JSON_FILE);
    }

    @Override
    public void onPostExecute(String json) {
        Log.d("Samuel", "onPostExecute: "+json);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<FootballTeams>>(){}.getType();
        ArrayList<FootballTeams> data = gson.fromJson(json, type);
        footballTeamsList.addAll(data);
        adapter.addData(footballTeamsList);
    }
}