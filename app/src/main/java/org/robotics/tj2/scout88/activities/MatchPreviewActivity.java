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

//import org.robotics.tj2.scout88.etc.MatchPreviewCalculations;
import org.robotics.tj2.scout88.etc.MatchPreviewCalculations;
import org.robotics.tj2.scout88.etc.Performance;
import org.robotics.tj2.scout88.R;
import org.robotics.tj2.scout88.etc.FirebaseInterface;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class MatchPreviewActivity extends AppCompatActivity {

    private int progressStatus = 50;
    private Handler handler = new Handler();
    public int gotThisManyTeamsData = 0;

    private TextView textBlueWinPerc;
    private TextView textRedWinPerc;
    private ProgressBar winPercBar;

    final public ArrayList<Performance> alpBlue1 = new ArrayList<>();
    final public ArrayList<Performance> alpBlue2 = new ArrayList<>();
    final public ArrayList<Performance> alpBlue3 = new ArrayList<>();
    final public ArrayList<Performance> alpRed1 = new ArrayList<>();
    final public ArrayList<Performance> alpRed2 = new ArrayList<>();
    final public ArrayList<Performance> alpRed3 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        textBlueWinPerc = (TextView) findViewById(R.id.blue_win_perc_text);
        textRedWinPerc = (TextView) findViewById(R.id.red_win_perc_text);
        winPercBar = (ProgressBar) findViewById(R.id.progressBar);

        textBlueWinPerc.setText(50 + "%");
        textRedWinPerc.setText(50 + "%");

        winPercBar.setProgress(progressStatus);

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

        blue1Number.setText(teamNums[0] + "");
        blue2Number.setText(teamNums[1] + "");
        blue3Number.setText(teamNums[2] + "");
        red1Number.setText(teamNums[3] + "");
        red2Number.setText(teamNums[4] + "");
        red3Number.setText(teamNums[5] + "");

        fbi.startMatchPreviewData(alpBlue1 , teamNums[0] , this);
        fbi.startMatchPreviewData(alpBlue2 , teamNums[1] , this);
        fbi.startMatchPreviewData(alpBlue3 , teamNums[2] , this);
        fbi.startMatchPreviewData(alpRed1 , teamNums[3] , this);
        fbi.startMatchPreviewData(alpRed2 , teamNums[4] , this);
        fbi.startMatchPreviewData(alpRed3 , teamNums[5] , this);

    }

    public void updateUI(){

        if(gotThisManyTeamsData >= 6){
            Log.v("databse" , "Got all team data");
            ArrayList<Performance>[] arrayForCalcs = new ArrayList[6];
            arrayForCalcs[0] = alpBlue1;
            arrayForCalcs[1] = alpBlue2;
            arrayForCalcs[2] = alpBlue3;
            arrayForCalcs[3] = alpRed1;
            arrayForCalcs[4] = alpRed2;
            arrayForCalcs[5] = alpRed3;
            MatchPreviewCalculations mpCalcs = new MatchPreviewCalculations(arrayForCalcs);

            Log.v("calculations" , "Blue Win Chance: " + mpCalcs.winChance());

            TextView p_value_text = (TextView) findViewById(R.id.p_value_txt);
            p_value_text.setText("blue p = " + mpCalcs.winChance());

            ArrayList<Double> pointRangeBlue = mpCalcs.pointIntA();
            ArrayList<Double> pointRangeRed = mpCalcs.pointIntO();

            TextView textPointsBlue = (TextView) findViewById(R.id.text_proj_points_blue);
            TextView textPointsRed = (TextView) findViewById(R.id.text_proj_points_red);

            int vWinPercBlue;

            if(pointRangeBlue.get(0) < 0){
                pointRangeBlue.set(0 , 0.0);
            }
            if(pointRangeRed.get(0) < 0){
                pointRangeRed.set(0 , 0.0);
            }

            double avgPointsBlue = (pointRangeBlue.get(0) + pointRangeBlue.get(1)) / 2.0;
            double avgPointsRed = (pointRangeRed.get(0) + pointRangeRed.get(1)) / 2.0;

            double rawPercBlue = ((avgPointsBlue / avgPointsRed));
            if(rawPercBlue > 1){
                vWinPercBlue = (int)((rawPercBlue - 1 + .5)*100);
            }else if (rawPercBlue < 1){
                vWinPercBlue = (int)((-1*(rawPercBlue - 1) + .5)*100);
            }else{
                vWinPercBlue = 50;
            }

            //final int winPercBlue = vWinPercBlue;
            final int winPercBlue = 100 - (int) (mpCalcs.winChance() * 100);
            textPointsBlue.setText(String.format("%.1f-%.1f" , pointRangeBlue.get(0) , pointRangeBlue.get(1)));
            textPointsRed.setText(String.format("%.1f-%.1f" , pointRangeRed.get(0) , pointRangeRed.get(1)));
            final double winPercRed = 100 - winPercBlue;

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
                                textBlueWinPerc.setText(progressStatus + "%");
                                int rwp = 100-progressStatus;
                                textRedWinPerc.setText(rwp + "%");

                            }
                        });
                        try{
                            Thread.sleep(sleepTime);
                        } catch (Exception e){

                        }


                    }
                }
            }).start();
        }
    }
}
