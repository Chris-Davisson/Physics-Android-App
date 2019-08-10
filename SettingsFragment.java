package com.example.lab7;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference pref = findPreference(key);
        BounceMass bm = getActivity().findViewById(R.id.bounceMass);
        switch (key) {
            case "SpringConstantNumberEditText":
                float val = Float.parseFloat(sharedPreferences.getString(key , "1.1"));
                if(val > .5f && val < 10.0f){
                    pref.setSummary(sharedPreferences.getString(key , "1.1"));
                }
                break;
            case "NumberOfCoils":
                pref.setSummary(Integer.toString(sharedPreferences.getInt(key , 8)));

                break;
            case "WeightDisplacimentNumberPicker":
                pref.setSummary(Integer.toString(sharedPreferences.getInt(key , 8)));
                break;
            case "WeightShapeListPreference":
                pref.setDefaultValue(sharedPreferences.getString(key , "Rectangle"));
                break;
            default:break;
        }


//        String shape = (String)findPreference("WeightShapeListPreference").getSummary();
//        int coilNum = Integer.parseInt((String)findPreference("NumberOfCoils").getSummary());
//        float stiffness = Float.parseFloat((String)findPreference("SpringConstantNumberEditText").getSummary());
//        int displace = Integer.parseInt((String)findPreference("WeightDisplacimentNumberPicker").getSummary());
//        bm.getStuffsNThings(shape, coilNum, stiffness , displace);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        onSharedPreferenceChanged(getPreferenceScreen().getSharedPreferences() , "SpringConstantNumberEditText");
        onSharedPreferenceChanged(getPreferenceScreen().getSharedPreferences() , "NumberOfCoils");
        onSharedPreferenceChanged(getPreferenceScreen().getSharedPreferences() , "WeightDisplacimentNumberPicker");
        onSharedPreferenceChanged(getPreferenceScreen().getSharedPreferences() , "WeightShapeListPreference");

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
