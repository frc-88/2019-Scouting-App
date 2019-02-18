package org.robotics.tj2.scout88;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;


public class DataSyncActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sync);

        final Button csvBtn = (Button) findViewById(R.id.upload_csv_button);

        final FirebaseInterface fbi = new FirebaseInterface(this);

        csvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean success = fbi.createAndUploadCSV(csvBtn);


            }
        });
    }
}

