package org.robotics.tj2.scout88.activities;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;
import org.robotics.tj2.scout88.etc.FirebaseInterface;
import org.robotics.tj2.scout88.R;


public class DataSyncActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_sync);

        final Button tbaButton = (Button) findViewById(R.id.download_match_data_btn);

        tbaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tbaButton.setText("Working...");
                tbaButton.setBackgroundColor(Color.parseColor("magenta"));

                String url = "https://www.thebluealliance.com/api/v3/event/2019nhgrs/matches/simple";
                JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("tbadata", "Success: " + response.toString());
                        tbaButton.setText("Complete!");
                        tbaButton.setBackgroundColor(Color.parseColor("green"));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.v("tbadata" , "Error" + error.toString());
                        tbaButton.setText("Failed!");
                        tbaButton.setBackgroundColor(Color.parseColor("red"));
                    }
                });

            }
        });

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

