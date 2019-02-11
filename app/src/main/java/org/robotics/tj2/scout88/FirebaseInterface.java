package org.robotics.tj2.scout88;

import android.content.Context;
import android.icu.util.Freezable;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseInterface {

    private final String COMPETITION_NAME = "TestCompetition";
    private final String COMPETITION_FLAG = "TC_";

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public FirebaseInterface(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(COMPETITION_NAME);
    }

    public FirebaseInterface(boolean init){
        database = FirebaseDatabase.getInstance();
        init();
        myRef = database.getReference(COMPETITION_NAME);
        Log.v("databse" , "setPersistence YES");
    }

    public boolean testConnection(){
        //FirebaseApp.initializeApp(context);
        //FirebaseApp fba = FirebaseApp.getInstance();
        //Log.v("databse" , fba.getName());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        Performance p = new Performance();
        ArrayList<String> cgo = new ArrayList<>();
        ArrayList<String> pnls = new ArrayList<>();
        for(int ii = 0; ii < 20; ii++){
            cgo.add("teleop");
            pnls.add("teleop");
        }
        //String[] cgo = {"teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop"};
        //p.setCargo(cgo);
        p.setComments("this was a great match");
        p.setDefense(20.5);
        p.setLevelOfClimb(2);
        p.setMatchNumber(5);
        p.setTeamNumber(8888);
        p.setStartingLevel(2);
        //String[] pnls = {"teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop"};
        //p.setPanels(pnls);

        myRef.child("performances").child(COMPETITION_FLAG+p.getMatchNumber()+"_"+p.getTeamNumber()).setValue(p);
        return true;
    }

    public void addPerformance(Performance p){
        myRef.child("performances").child(COMPETITION_FLAG+p.getMatchNumber()+"_"+p.getTeamNumber()).setValue(p);
    }
    public void init(){
        database.setPersistenceEnabled(true);
        database.setPersistenceCacheSizeBytes(100000000);
    }
}
