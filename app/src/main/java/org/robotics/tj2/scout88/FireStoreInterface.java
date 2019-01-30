package org.robotics.tj2.scout88;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FireStoreInterface {

    private FirebaseFirestore mFirestore;
    private String week_id = "8MCo1AJhi9YV4tI0T28N";
    private boolean lastQuerySuccess = false;

    public FireStoreInterface(){
        mFirestore = FirebaseFirestore.getInstance();
    }

    public void initDB(){
            Log.v("databse" , "syncDB: called ");
            mFirestore.collection("Week1").get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot documentSnapshots) {
                            Log.v("databse" , "onSuccess: called");
                            Log.v("databse" , "" + documentSnapshots.toString());
                            lastQuerySuccess = true;
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.v("databse" , "onFailure: called");
                    lastQuerySuccess = false;
                }
            });
    }

    public void getTable(String tableName){
        mFirestore.collection("Week1/" + week_id + "/" + tableName).get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        List<DocumentSnapshot> dsl = documentSnapshots.getDocuments();

                        ArrayList<Map<String , Object>> teamsTable = new ArrayList<Map<String, Object>>();
                        for(int i = 0; i < dsl.size(); i++){
                            DocumentSnapshot ds = dsl.get(i);
                            Map<String, Object> docMap = ds.getData();
                            teamsTable.add(docMap);
                            Log.v("databse", docMap.toString());
                        }
                    }
                });
    }

    public boolean pushPerformance(){

        return false;
    }
}
