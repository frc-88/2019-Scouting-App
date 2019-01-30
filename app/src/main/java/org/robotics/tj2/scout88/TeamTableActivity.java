package org.robotics.tj2.scout88;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class TeamTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_table);

        FireStoreInterface fbi = new FireStoreInterface();
        fbi.initDB();
        fbi.getTable("Teams");

        String path = "/data/data/" + getPackageName() + "/sample.db";
        DBInterface.initDB(path);


    }

}
