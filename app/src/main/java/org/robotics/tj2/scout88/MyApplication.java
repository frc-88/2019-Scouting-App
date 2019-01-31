package org.robotics.tj2.scout88;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;

public class MyApplication extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("databse" , "got to My Application()");
        FirebaseApp fba = FirebaseApp.initializeApp(this);
        Log.v("databse" , fba.toString());
    }
}
