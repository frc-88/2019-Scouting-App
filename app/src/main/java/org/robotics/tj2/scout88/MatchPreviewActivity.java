package org.robotics.tj2.scout88;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MatchPreviewActivity extends AppCompatActivity {

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
        ArrayList<Performance> alpBlue = fbi.getMatchPreviewData(Arrays.copyOfRange(teamNums , 0 , 2));
        ArrayList<Performance> alpRed = fbi.getMatchPreviewData(Arrays.copyOfRange(teamNums , 3 , 5));

        TextView blue1Number = (TextView) findViewById(R.id.blue1);
        TextView blue2Number = (TextView) findViewById(R.id.blue2);
        TextView blue3Number = (TextView) findViewById(R.id.blue3);
        TextView red1Number = (TextView) findViewById(R.id.red1);

        blue1Number.setText(teamNums[0] + "");
        blue2Number.setText(teamNums[1] + "");
        blue3Number.setText(teamNums[2] + "");
        red1Number.setText(teamNums[3] + "");

    }
}
