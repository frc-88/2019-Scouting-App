package org.robotics.tj2.scout88;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity
        implements Ingame.OnFragmentInteractionListener,
        Pregame.OnFragmentInteractionListener{

    private Context context = this;
    private Fragment goals_frag = new Ingame();
    private Fragment pregame_frag = new Pregame();
    private FragmentManager fm = getSupportFragmentManager();
    Fragment active = pregame_frag;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(context , "Screen 1" , Toast.LENGTH_LONG);
                    fm.beginTransaction().hide(active).show(pregame_frag).commit();
                    active = pregame_frag;
                    return true;
                case R.id.navigation_dashboard:
                    Toast.makeText(context , "Screen 2" , Toast.LENGTH_LONG);
                    fm.beginTransaction().hide(active).show(goals_frag).commit();
                    active = goals_frag;

                    return true;
                case R.id.navigation_notifications:
                    Toast.makeText(context , "Screen 3" , Toast.LENGTH_LONG);
                    fm.beginTransaction().hide(active);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.main_container,goals_frag, "2").hide(goals_frag).commit();
        fm.beginTransaction().add(R.id.main_container,pregame_frag, "1").commit();

        //Application app = new Application();

        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("test1");
        FirebaseInterface fbi = new FirebaseInterface(this);

    }


    public void onFragmentInteraction(Uri uri){
        //leave blank
    }



}

