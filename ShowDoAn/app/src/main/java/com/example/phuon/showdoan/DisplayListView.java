package com.example.phuon.showdoan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;

public class DisplayListView extends AppCompatActivity {

    String jsonString;
    JSONObject jsonObject;
    JSONArray jsonArray;
    ContactsAdapter contactsAdapter;
    ListView listView;
    SwipeRefreshLayout swipeRefreshLayout;
    Animation downtoup;
    RelativeLayout displayLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_view);

        displayLayer = findViewById(R.id.displayLayer);
        downtoup = AnimationUtils.loadAnimation(this,R.anim.downtoup);
        displayLayer.startAnimation(downtoup);

        swipeRefreshLayout = findViewById(R.id.swipeR);
        contactsAdapter = new ContactsAdapter(this,R.layout.row);
        listView = (ListView) findViewById(R.id.listView);
        jsonString = getIntent().getExtras().getString("json_data");

        listView.setAdapter(contactsAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = new Intent(DisplayListView.this,MainActivity.class);
                startActivity(intent);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },1200);
            }
        });
        try {
            jsonObject = new JSONObject(jsonString);
            //jsonArray = jsonObject.getJSONArray("contacts");
            jsonArray = jsonObject.getJSONArray("data");
            int count = 0;
            int check = 0;
            String id;
            float temp,humi;
            long time;
            while (count < jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(jsonArray.length() - count -1);
                id = JO.getString("mssv");
                temp = JO.getLong("temperature");
                humi = JO.getLong("humid");
                time = JO.getLong("time");
                Contacts contacts = new Contacts(id,temp,humi,time);
                if (id.toString().equals("2"))
                {
                    contactsAdapter.add(contacts);
                    check++;
                }
                if (check >= 30) break;
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
