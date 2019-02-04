package org.robotics.tj2.scout88;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class MyApplication extends Application {

    public FirebaseInterface fbi;

    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("databse" , "got to My Application()");
        FirebaseApp fba = FirebaseApp.initializeApp(this);
        Log.v("databse" , fba.toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();;
        database.setPersistenceEnabled(true);
        database.setPersistenceCacheSizeBytes(100000000);
    }
}
