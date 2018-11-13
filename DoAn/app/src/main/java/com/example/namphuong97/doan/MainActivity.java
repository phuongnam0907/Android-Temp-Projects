package com.example.namphuong97.doan;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String mssv;
    ListView listView;
    ArrayList<Device> list;
    CustomAdapter arrayAdapter;
    //Retrofit trong android
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mssv = getIntent().getExtras().getString("id_mssv");
        listView = findViewById(R.id.listview);
        list = new ArrayList<>();
        arrayAdapter = new CustomAdapter(this, R.layout.item_list_view, list, mssv);
        listView.setAdapter(arrayAdapter);

        ApiController controller = new ApiController();
        controller.getData().addListener(new ApiController.ApiListenser() {
            @Override
            public void onGetData(ApiData data) {
                if (data != null) {
                    list.clear();
                    list.addAll(data.data);
                    arrayAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(Throwable t) {

            }
        });
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
            }
        }, 20000);

    }

}
