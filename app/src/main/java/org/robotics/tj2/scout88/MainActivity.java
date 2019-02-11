package org.robotics.tj2.scout88;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.prefs.Preferences;


public class MainActivity extends AppCompatActivity
        implements Ingame.OnFragmentInteractionListener,
        Pregame.OnFragmentInteractionListener,
        Postgame.OnFragmentInteractionListener{

    private Context context = this;
    public Performance currentMatch = new Performance();
    private Ingame goals_frag = new Ingame(currentMatch);
    private Pregame pregame_frag = new Pregame(currentMatch);
    private Postgame postgame_frag = new Postgame(currentMatch);
    private FragmentManager fm = getSupportFragmentManager();
    Fragment active = pregame_frag;
    private DrawerLayout mDrawerLayout;

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
                    fm.beginTransaction().hide(active).show(postgame_frag).commit();
                    active = postgame_frag;
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
        fm.beginTransaction().add(R.id.main_container,postgame_frag, "3").hide(postgame_frag).commit();
        fm.beginTransaction().add(R.id.main_container,pregame_frag, "1").commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent inputIntent = getIntent();
        Bundle inputBundle = inputIntent.getExtras();

        if(inputBundle != null){
            pregame_frag.currentScouterName = inputBundle.getString("scouter_name");
            Log.v("activity_switch" , inputBundle.getString("scouter_name"));
            pregame_frag.autoFillMatchNumber = inputBundle.getString("new_match_number");
            Log.v("activity_switch" , inputBundle.getString("new_match_number"));
        }
        else{
            FirebaseInterface fbi = new FirebaseInterface(true);
        }

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        String teamPref = sharedPref.getString("example_list" , "Def");
        getSupportActionBar().setTitle("Scout88: " + teamPref);

        if (teamPref.contains("Red")){
            toolbar.setBackgroundColor(getResources().getColor(R.color.redPrimary));
            setStatusBarColor(R.color.redStatusBar);
        }

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_hamburger);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        Log.v("88settings" , menuItem.getOrder() + "");
                        switch(menuItem.getTitle().toString()){
                            case "Settings":
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this , SettingsActivity.class);
                                startActivity(intent);
                                break;
                        }
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();


                        return true;
                    }
                });
    }


    public void onFragmentInteraction(Uri uri){
        //leave blank
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setStatusBarColor(int res){
        Window window = this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,res));
    }





}

