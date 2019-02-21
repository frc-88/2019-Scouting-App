package org.robotics.tj2.scout88.activities;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.robotics.tj2.scout88.etc.FirebaseInterface;
import org.robotics.tj2.scout88.R;


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

                csvBtn.setText("Working...");
                csvBtn.setBackgroundColor(Color.parseColor("magenta"));
                boolean success = fbi.createAndUploadCSV(csvBtn);


            }
        });
    }
}

