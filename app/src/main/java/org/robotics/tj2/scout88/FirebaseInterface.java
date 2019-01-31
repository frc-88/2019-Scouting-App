package org.robotics.tj2.scout88;

import android.content.Context;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseInterface {

    public FirebaseInterface(Context context){
        //FirebaseApp.initializeApp(context);
        //FirebaseApp fba = FirebaseApp.getInstance();
        //Log.v("databse" , fba.getName());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("test1");

        Performance p = new Performance();
        ArrayList<String> cgo = new ArrayList<>();
        ArrayList<String> pnls = new ArrayList<>();
        for(int ii = 0; ii < 20; ii++){
            cgo.add("teleop");
            pnls.add("teleop");
        }
        //String[] cgo = {"teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop"};
        p.setCargo(cgo);
        p.setComments("this was a good match");
        p.setDefense(20.5);
        p.setLevelOfClimb(2);
        p.setMatchNumber(1);
        p.setTeamNumber(8888);
        p.setStartingLevel(2);
        //String[] pnls = {"teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop"};
        p.setPanels(pnls);

        myRef.setValue(p);
    }
}
