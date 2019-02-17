package org.robotics.tj2.scout88;

import android.content.Context;
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

        Button csvBtn = (Button) findViewById(R.id.upload_csv_button);

        final FirebaseInterface fbi = new FirebaseInterface();

        csvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(getString(R.string.COMPETITION) , Context.MODE_PRIVATE));
                    osw.write("Team Number,Match Number,High Cargo,Middle Cargo,Low Cargo,Rocket Cargo,High Panels,Middle Panels,Low Panels,Rocket Panels,Total Rocket,Cargo 0,Cargo 1,Cargo 2,Cargo 3,Cargo 4,Cargo 5,Cargo 6,Cargo 7,Ship Cargo,Panel 0,Panel 1,Panel 2,Panel 3,Panel 4,Panel 5,Panel 6,Panel 7,Ship Panels,Total Cargo,Total Panels,Total Pieces,Starting Element,Starting Level,Sandstorm Cross,Endgame Level,MVP,Strong Defense,Oof,Broken,No Show\n");
                    ArrayList<Iterable<DataSnapshot>> dsi = fbi.getAllData();
                    for (DataSnapshot ds : dsi.get(0)) {
                        Performance p = ds.getValue(Performance.class);
                        Log.v("databse", p.toString());

                        osw.write(p.getTeamNumber() + ",");
                        osw.write(p.getMatchNumber() + ",");
                        osw.write(p.getNumHighCargo() + ",");
                        osw.write(p.getNumMedCargo() + ",");
                        osw.write(p.getNumLowCargo() + ",");
                        int rocketCargo = p.getNumHighCargo() + p.getNumMedCargo() + p.getNumLowCargo();
                        osw.write(rocketCargo + ",");
                        osw.write(p.getNumHighPanels() + ",");
                        osw.write(p.getNumMedPanels() + ",");
                        osw.write(p.getNumLowPanels() + ",");
                        int rocketPanels = p.getNumHighPanels() + p.getNumMedPanels() + p.getNumLowPanels();
                        osw.write(rocketPanels + ",");
                        int totalRocket = rocketCargo + rocketPanels;
                        osw.write(totalRocket + ",");



                    }
                } catch (Exception e){

                }
            }
        });
    }
}
