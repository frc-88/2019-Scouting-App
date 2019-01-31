package org.robotics.tj2.scout88;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseInterface {

    public FirebaseInterface(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("test1");

        myRef.setValue("testes2");
    }
}
