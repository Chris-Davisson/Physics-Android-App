package com.example.lab7;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    public int mSecs = 100;
    public boolean isOn = false;
    public int coilNum;
    public String shape = "Picture";
    public float stiffness;
    public int displace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orien = getResources().getConfiguration().orientation;
        if(orien == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
        }
        else{
            setContentView(R.layout.land);
        }



        final Handler clockHandler = new Handler();
        final Runnable clockTimer = new Runnable() {
            @Override
            public void run() {

                BounceMass bm = findViewById(R.id.bounceMass);
                bm.physics(mSecs);
                clockHandler.postDelayed(this , mSecs);
                bm.invalidate();
                //Toast.makeText(getApplicationContext() , "test text" , Toast.LENGTH_SHORT).show();

                }
            };

        Button button = findViewById(R.id.actionBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isOn){
                    clockTimer.run();
                    ToneGenerator tg = new ToneGenerator(AudioManager.STREAM_NOTIFICATION,100);
                    tg.startTone(ToneGenerator.TONE_PROP_BEEP);
                    tg.release();
                    isOn = true;
                }
                else{
                    clockHandler.removeCallbacks(clockTimer);
                    isOn = false;
                }
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences thingy1 = PreferenceManager.getDefaultSharedPreferences(this);
        shape = thingy1.getString("WeightShapeListPreference" , "Picture");
        stiffness = Float.parseFloat(thingy1.getString("SpringConstantNumberEditText" , "1.1"));
        coilNum = thingy1.getInt("NumberOfCoils" , 8);
        displace = thingy1.getInt("WeightDisplacimentNumberPicker" , 8);

        BounceMass bm = findViewById(R.id.bounceMass);
        bm.mShape = shape;
        bm.mStiffness = stiffness;
        bm.mDisplace = displace;
        bm.mCoilNum = coilNum;
        bm.invalidate();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// This adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here
        int id = item.getItemId();
        if (id == R.id.about_action) {
            Toast.makeText(this, R.string.about_action, Toast.LENGTH_SHORT).show();
            return true;
        }
        if(id == R.id.settings_action) {
            Intent settings = new Intent(this , SettingsActivity.class);
            startActivity(settings);
        }
        return super.onOptionsItemSelected(item);
    }
}
