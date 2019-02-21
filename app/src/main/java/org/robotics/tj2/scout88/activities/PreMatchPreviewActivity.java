package org.robotics.tj2.scout88.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import org.robotics.tj2.scout88.R;

public class PreMatchPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_match_preview);

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.neutral_activity_status_bar));

        Button goToNextBtn = (Button) findViewById(R.id.go_to_match_preview);

        final EditText[] inputBoxes = new EditText[6];
        inputBoxes[0] = (EditText) findViewById(R.id.input_blue_1);
        inputBoxes[1] = (EditText) findViewById(R.id.input_blue_2);
        inputBoxes[2] = (EditText) findViewById(R.id.input_blue_3);
        inputBoxes[3] = (EditText) findViewById(R.id.input_red_1);
        inputBoxes[4] = (EditText) findViewById(R.id.input_red_5);
        inputBoxes[5] = (EditText) findViewById(R.id.input_red_6);

        goToNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] teamNums = new int[6];
                int n = 0;
                try {
                    for (n = 0; n < inputBoxes.length; n++) {
                        teamNums[n] = Integer.parseInt(inputBoxes[n].getText().toString());
                    }
                    Intent intent = new Intent();
                    intent.setClass(PreMatchPreviewActivity.this , MatchPreviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putIntArray("team_numbers" , teamNums);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }catch (Exception e){
                    Log.v("activity_switch" , "team number could not be parsed" + e.toString());
                    inputBoxes[n].setError("Enter team Number");
                }


            }
        });
    }
}
