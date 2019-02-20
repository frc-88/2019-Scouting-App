package org.robotics.tj2.scout88;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MatchPreviewActivity extends AppCompatActivity {

    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent inputIntent = getIntent();
        Bundle inputBundle = inputIntent.getExtras();
        int[] teamNums = new int[6];

        if(inputBundle != null){
            teamNums = inputBundle.getIntArray("team_numbers");
        }

        final FirebaseInterface fbi = new FirebaseInterface();
        ArrayList<Performance> alpBlueAll = fbi.getMatchPreviewData(Arrays.copyOfRange(teamNums , 0 , 2));
        ArrayList<Performance> alpRedAll = fbi.getMatchPreviewData(Arrays.copyOfRange(teamNums , 3 , 5));

        TextView blue1Number = (TextView) findViewById(R.id.blue1);
        TextView blue2Number = (TextView) findViewById(R.id.blue2);
        TextView blue3Number = (TextView) findViewById(R.id.blue3);
        TextView red1Number = (TextView) findViewById(R.id.red1);
        TextView red2Number = (TextView) findViewById(R.id.red2);
        TextView red3Number = (TextView) findViewById(R.id.red3);

        blue1Number.setText(teamNums[0] + "");
        blue2Number.setText(teamNums[1] + "");
        blue3Number.setText(teamNums[2] + "");
        red1Number.setText(teamNums[3] + "");
        red2Number.setText(teamNums[4] + "");
        red3Number.setText(teamNums[5] + "");

        final int winPercBlue = 67;
        final int winPercRed = 100 - winPercBlue;

        final ProgressBar winPercBar = (ProgressBar) findViewById(R.id.progressBar);
        //winPercBar.getProgressDrawable().setColorFilter(0x303F9F , PorterDuff.Mode.SRC_IN);
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                int direction = 1;
                int sleepTime = 50;
                while (progressStatus != winPercBlue) {

                    progressStatus += direction;
                    // Update the progress bar and display the
                    //current value in the text view

                    if(progressStatus+10 == winPercBlue){
                        sleepTime = 100;
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            winPercBar.setProgress(progressStatus);

                        }
                    });
                    try {

                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
