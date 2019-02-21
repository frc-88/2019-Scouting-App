package org.robotics.tj2.scout88.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.robotics.tj2.scout88.etc.MatchPreviewCalculations;
import org.robotics.tj2.scout88.etc.Performance;
import org.robotics.tj2.scout88.R;
import org.robotics.tj2.scout88.etc.FirebaseInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class MatchPreviewActivity extends AppCompatActivity {

    private int progressStatus = 50;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.neutral_activity_status_bar));

        toolbar.setTitleTextColor(getResources().getColor(R.color.black));
        setSupportActionBar(toolbar);
        Intent inputIntent = getIntent();
        Bundle inputBundle = inputIntent.getExtras();
        int[] teamNums = new int[6];

        if(inputBundle != null){
            teamNums = inputBundle.getIntArray("team_numbers");
        }

        TextView blue1Number = (TextView) findViewById(R.id.blue1);
        TextView blue2Number = (TextView) findViewById(R.id.blue2);
        TextView blue3Number = (TextView) findViewById(R.id.blue3);
        TextView red1Number = (TextView) findViewById(R.id.red1);
        TextView red2Number = (TextView) findViewById(R.id.red2);
        TextView red3Number = (TextView) findViewById(R.id.red3);

        final FirebaseInterface fbi = new FirebaseInterface();

        ArrayList<Performance> alpBlue1 = new ArrayList<>();
        ArrayList<Performance> alpBlue2 = new ArrayList<>();
        ArrayList<Performance> alpBlue3 = new ArrayList<>();
        ArrayList<Performance> alpRed1 = new ArrayList<>();
        ArrayList<Performance> alpRed2 = new ArrayList<>();
        ArrayList<Performance> alpRed3 = new ArrayList<>();
        boolean reachedTimeout = false;
        for( int ii = 0; ii < 6; ii++){
            int timeoutCounter = 0;
            fbi.startMatchPreviewData(teamNums[ii]);
            while(!fbi.isGotCurrentPerfs()){
                try {
                    wait(100);
                }catch (Exception e){
                    reachedTimeout = true;
                    break;
                }
                timeoutCounter++;
                if(timeoutCounter > 10*20){
                    reachedTimeout = true;
                    break;
                }
            }
            if(reachedTimeout){
                break;
            }
            switch (ii){
                case 0:
                    alpBlue1 = fbi.getCurrentlyLoadedPerformances();
                    break;
                case 1:
                    alpBlue2 = fbi.getCurrentlyLoadedPerformances();
                    break;
                case 2:
                    alpBlue3 = fbi.getCurrentlyLoadedPerformances();
                    break;
                case 3:
                    alpRed1 = fbi.getCurrentlyLoadedPerformances();
                    break;
                case 4:
                    alpRed2 = fbi.getCurrentlyLoadedPerformances();
                    break;
                case 5:
                    alpRed3  = fbi.getCurrentlyLoadedPerformances();
                    break;
            }
        }
        if(reachedTimeout){
            blue1Number.setText("timeout");
            blue2Number.setText("timeout");
            blue3Number.setText("timeout");
            red1Number.setText("timeout");
            red2Number.setText("timeout");
            red3Number.setText("timeout");
        }else{

        }

        blue1Number.setText(teamNums[0] + "");
        blue2Number.setText(teamNums[1] + "");
        blue3Number.setText(teamNums[2] + "");
        red1Number.setText(teamNums[3] + "");
        red2Number.setText(teamNums[4] + "");
        red3Number.setText(teamNums[5] + "");

        final int winPercBlue = 88;
        final int winPercRed = 100 - winPercBlue;

        final ProgressBar winPercBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView textBlueWinPerc = (TextView) findViewById(R.id.blue_win_perc_text);
        final TextView textRedWinPerc = (TextView) findViewById(R.id.red_win_perc_text);

        ArrayList<Performance>[] arrayForCalcs = new ArrayList[6];
        arrayForCalcs[0] = alpBlue1;
        arrayForCalcs[1] = alpBlue2;
        arrayForCalcs[2] = alpBlue3;
        arrayForCalcs[3] = alpRed1;
        arrayForCalcs[4] = alpRed2;
        arrayForCalcs[5] = alpRed3;
        MatchPreviewCalculations mpCalcs = new MatchPreviewCalculations(arrayForCalcs);

        Log.v("databse" , "Blue1 Size: " + alpBlue1.size());
        Log.v("calcs" , "WinChance: " + mpCalcs.winChance() + "");


        //winPercBar.getProgressDrawable().setColorFilter(0x303F9F , PorterDuff.Mode.SRC_IN);
        // Start long running operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                int direction = -1;
                int sleepTime = 25;

                if(winPercBlue >= 50){
                    direction = 1;
                }

                while (progressStatus != winPercBlue) {

                    progressStatus += direction;
                    // Update the progress bar and display the
                    //current value in the text view

                    if(Math.abs(winPercBlue - progressStatus) == 10){
                        sleepTime *= 2;
                    }
                    handler.post(new Runnable() {
                        public void run() {
                            winPercBar.setProgress(progressStatus);
                            textBlueWinPerc.setText(progressStatus + "");
                            int rwp = 100-progressStatus;
                            textRedWinPerc.setText(rwp + "");

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
