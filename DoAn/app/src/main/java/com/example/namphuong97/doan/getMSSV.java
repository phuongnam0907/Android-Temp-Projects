package com.example.namphuong97.doan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.provider.LiveFolders.DESCRIPTION;
import static android.provider.MediaStore.Files.FileColumns.TITLE;

public class getMSSV extends AppCompatActivity {

    String mssv;
    EditText numText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_mssv);

        numText = findViewById(R.id.numText);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mssv = String.valueOf(numText.getText());

                    Intent intent = new Intent(getMSSV.this,MainActivity.class);
                    intent.putExtra("id_mssv",mssv);
                    startActivity(intent);

            }
        });
    }
}
