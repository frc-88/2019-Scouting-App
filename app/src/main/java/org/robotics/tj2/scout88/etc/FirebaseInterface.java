package org.robotics.tj2.scout88.etc;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.robotics.tj2.scout88.R;
import org.robotics.tj2.scout88.activities.MatchPreviewActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class FirebaseInterface {


    private final String COMPETITION_NAME = "SEMA";
    private final String COMPETITION_FLAG = "SE_";


    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference csvStorageRef;
    private DatabaseReference myRef;
    private Context context;

    private boolean gotCurrentBlue1 = false;
    private boolean gotCurrentBlue2 = false;
    private boolean gotCurrentBlue3 = false;
    private boolean gotCurrentRed1 = false;
    private boolean gotCurrentRed2 = false;
    private boolean gotCurrentRed3 = false;


    public FirebaseInterface(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(COMPETITION_NAME);

    }

    public FirebaseInterface(boolean init){
        database = FirebaseDatabase.getInstance();
        init();
        myRef = database.getReference(COMPETITION_NAME);
        myRef.keepSynced(true);
        Log.v("databse" , "setPersistence YES");
    }

    public FirebaseInterface(Context context){
        this();
        this.context = context;
        FirebaseStorage storage = FirebaseStorage.getInstance();
        // Create a storage reference from our app

        // Create a reference to "mountains.jpg"
        //csvStorageRef = storageRef.child(COMPETITION_FLAG + ".csv");
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
        p.setClimb_Level(2);
        p.setMatch_Number(5);
        p.setTeam_Number(8888);
        p.setStarting_Position(2);
        //String[] pnls = {"teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop","teleop"};
        //p.setPanels(pnls);

        myRef.child("performances").child(COMPETITION_FLAG+p.getMatch_Number()+"_"+p.getTeam_Number()).setValue(p);
        return true;
    }


    public void addPerformance(Performance p){
        myRef.child("performances").child(COMPETITION_FLAG+p.getMatch_Number()+"_"+p.getTeam_Number()).setValue(p);
    }
    public void init(){
        database.setPersistenceEnabled(true);
        database.setPersistenceCacheSizeBytes(100000000);
    }

    private boolean returnVal = false;

    public boolean createAndUploadCSV(final Button uploadBtn){

        myRef.child("performances").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("databse" , "got here 0");
                Iterable<DataSnapshot> dsi = dataSnapshot.getChildren();
                try {
                    File myFile = new File(context.getFilesDir() + "/" + context.getString(R.string.COMPETITION) + ".csv");
                    //FileOutputStream osw = context.openFileOutput(myFile.getName() , Context.MODE_PRIVATE);
                    FileOutputStream osw = new FileOutputStream(myFile);

                    Log.v("databse" , "fullPathtoCSV: " + myFile.getAbsolutePath());

                    String csvHeader = "Team Number,Match Number,High Cargo,Middle Cargo,Low Cargo,High Cargo Sandstorm,Middle Cargo Sandstorm,Low Cargo Sandstorm,Rocket Cargo,High Panels,Middle Panels,Low Panels,High Panels Sandstorm,Middle Panels Sandstorm,Low Panels Sandstorm,Rocket Panels,Total Rocket,Cargo 0,Cargo 1,Cargo 2,Cargo 3,Cargo 4,Cargo 5,Cargo 6,Cargo 7,Ship Cargo,Panel 0,Panel 1,Panel 2,Panel 3,Panel 4,Panel 5,Panel 6,Panel 7,Ship Panels,Total Cargo,Total Panels,Total Pieces,Starting Element,Starting Level,Sandstorm Cross,Endgame Level,MVP,Strong Defense,Oof,Broken,No Show\n";
                    osw.write(csvHeader.getBytes());
                    int lastLastMatchNumber = 0;
                    for (DataSnapshot ds : dsi) {
                        Performance p = ds.getValue(Performance.class);
                        Log.v("databse", p.toString());

                        String row = "";

                        row = row + p.getTeam_Number() + ",";
                        row = row + p.getMatch_Number() + ",";
                        row = row + p.getHigh_Cargo() + ",";
                        row = row + p.getMiddle_Cargo() + ",";
                        row = row + p.getLow_Cargo_Rocket() + ",";
                        row = row + p.getSandstorm_High_Cargo() + ",";
                        row = row + p.getSandstorm_Middle_Cargo() + ",";
                        row = row + p.getSandstorm_Low_Cargo_Rocket() + ",";
                        int rocketCargo = p.getHigh_Cargo() + p.getMiddle_Cargo() + p.getLow_Cargo_Rocket() + p.getSandstorm_High_Cargo() + p.getSandstorm_Middle_Cargo() + p.getSandstorm_Low_Cargo_Rocket();
                        row = row + rocketCargo + ",";
                        row = row + p.getHigh_Panels() + ",";
                        row = row + p.getMiddle_Panels() + ",";
                        row = row + p.getLow_Panels_Rocket() + ",";
                        row = row + p.getSandstorm_High_Panels() + ",";
                        row = row + p.getSandstorm_Middle_Panels() + ",";
                        row = row + p.getSandstorm_Low_Panels_Rocket() + ",";
                        int rocketPanels = p.getHigh_Panels() + p.getMiddle_Panels() + p.getLow_Panels_Rocket() +p.getSandstorm_High_Panels() + p.getSandstorm_Middle_Panels() + p.getSandstorm_Low_Panels_Rocket();
                        row = row + rocketPanels + ",";
                        int totalRocket = rocketCargo + rocketPanels;
                        row = row + totalRocket + ",";
                        int shipCargo = 0;
                        for(int n = 0; n < p.getCargo().size(); n ++){
                            row = row + p.getCargo().get(n) + ",";
                            if(p.getCargo().get(n) >= 1){
                                shipCargo++;
                            }
                        }
                        row = row + shipCargo + ",";
                        int shipPanels = 0;
                        for(int n = 0; n < p.getPanels().size(); n ++){
                            row = row + p.getPanels().get(n) + ",";
                            if(p.getPanels().get(n) >= 1){
                                shipPanels++;
                            }
                        }
                        row = row + shipPanels + ",";
                        int totalCargo = shipCargo + rocketCargo;
                        row = row + totalCargo + ",";
                        int totalPanels = shipPanels + rocketPanels;
                        row = row + totalPanels + ",";
                        int totalPieces = totalCargo + totalPanels;
                        row = row + totalPieces + ",";

                        row = row + p.getStarting_Game_Piece() + ",";
                        row = row + p.getStarting_Position() + ",";
                        row = row + p.getSandstorm_Cross() + ",";
                        row = row + p.getClimb_Level() + ",";
                        row = row + p.getMVP() + ",";
                        row = row + p.getStrong_Defense() + ",";
                        row = row + p.getOof() + ",";
                        row = row + p.getBroken_Or_DCed() + ",";
                        row = row + p.getNo_Show() + "\n";

                        Log.v("databse" , "csvRow: " + row);
                        osw.write(row.getBytes());
                        try{
                            if(p.getMatch_Number() > lastLastMatchNumber){
                                lastLastMatchNumber = p.getMatch_Number();
                            }
                        } catch(Exception e){
                            Log.v("databse" , e.toString());
                        }

                    }
                    osw.close();
                    FirebaseStorage storage = FirebaseStorage.getInstance();
                    csvStorageRef = storage.getReferenceFromUrl("gs://scout88-4a3e0.appspot.com/" + COMPETITION_FLAG + "uptoMatch" + lastLastMatchNumber + ".csv");
                    writeCsvToCloud(context.getFilesDir() + "/" + context.getString(R.string.COMPETITION) + ".csv" , uploadBtn);
                    Log.v("databse" , "got to end of writeCSV");

                } catch (Exception e){
                    Log.v("databse" , e.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return returnVal;
    }

    public boolean writeCsvToCloud(String filepath , final Button uploadBtn){
        Uri file = Uri.fromFile(new File(context.getFilesDir() + "/" + context.getString(R.string.COMPETITION) + ".csv"));

        File testFile = new File(filepath);


        Log.v("databse" , "file readable: " + testFile.canRead());
        Log.v("databse" , "file path: " + testFile.getAbsolutePath());

        UploadTask uploadTask = csvStorageRef.putFile(file);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v("databse" , "Upload Storage Failed: " + e.toString());
                e.printStackTrace();
                returnVal = false;
                uploadBtn.setText("Upload Failed.");
                uploadBtn.setBackgroundColor(Color.RED);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.v("databse" , "Upload Storage Success!");
                returnVal = true;
                uploadBtn.setText("Upload Successful");
                uploadBtn.setBackgroundColor(Color.parseColor("green"));
            }
        });
        return returnVal;
    }

    public void startMatchPreviewData(final ArrayList<Performance> alp , int teamNum , final MatchPreviewActivity mpa){

        Query q = myRef.child("performances").orderByChild("teamNumber").equalTo(teamNum);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds : dataSnapshot.getChildren()){
                        Performance p = ds.getValue(Performance.class);
                        p.totals();
                        alp.add(p);
                        Log.v("databse" , "Added match for preview: " + p.getTeam_Number() + " " + p.getMatch_Number());
                    }
                    mpa.gotThisManyTeamsData++;
                    mpa.updateUI();
                }
                //gotCurrentPerfs = true;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
