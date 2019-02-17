package org.robotics.tj2.scout88;

import android.content.Context;
import android.icu.util.Freezable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.OutputStreamWriter;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class FirebaseInterface {

    private final String COMPETITION_NAME = "Week_Zero";
    private final String COMPETITION_FLAG = "W0_";

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String csvFilePath;

    public FirebaseInterface(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(COMPETITION_NAME);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

        // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child("mountains.jpg");

        // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");
    }

    public FirebaseInterface(boolean init){
        database = FirebaseDatabase.getInstance();
        init();
        myRef = database.getReference(COMPETITION_NAME);
        Log.v("databse" , "setPersistence YES");
    }

    public FirebaseInterface(String csvFilePath){
        this();
        this.csvFilePath = csvFilePath;
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
        //p.setDefense(20.5);
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

    public ArrayList<Iterable<DataSnapshot>> getAllData(){

        final ArrayList<Iterable<DataSnapshot>> adsi = new ArrayList<>();
        myRef.child("performances").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("databse" , "got here 0");
                Iterable<DataSnapshot> dsi = dataSnapshot.getChildren();
                adsi.add(dsi);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return adsi;
    }

    public class Performances{
        private ArrayList<Performance> performances = new ArrayList<>();

        public Performances(){
        }

        public ArrayList<Performance> getPerformances() {
            return performances;
        }

        public void setPerformances(ArrayList<Performance> performances) {
            this.performances = performances;
        }
    }
}
