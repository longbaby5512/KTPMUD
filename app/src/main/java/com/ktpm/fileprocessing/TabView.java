package com.ktpm.fileprocessing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.ktpm.fileprocessing.Fragment.FreqFragment;
import com.ktpm.fileprocessing.Fragment.InfoFragment;
import com.ktpm.fileprocessing.Fragment.TimeFragment;
import com.ktpm.fileprocessing.SignalProcessing.Complex;
import com.ktpm.fileprocessing.SignalProcessing.FFT;
import com.ktpm.fileprocessing.SignalProcessing.Helper;


public class TabView extends AppCompatActivity {
    public int numChecked;
    public String[] files = new String[numChecked];
    public double[][] data = new double[numChecked][];
    public  double[][] dataRaw = new double[numChecked][];
    public int[] posChecked = new int[numChecked];
    public Complex[][] fft;
    public double[][] absFFT;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_view);

        ActionBar toolbar = getSupportActionBar();
        assert toolbar != null;
        toolbar.setDisplayHomeAsUpEnabled(true);
        toolbar.setDisplayShowHomeEnabled(true);

        // Get Data
        Intent intent = getIntent();
        getData(intent);


        // Set data to fragment
//        Bundle bundle = new Bundle();
//        bundle.putInt("NO. CHECK", numChecked);
//        bundle.putStringArray("FILES DATA NAME", files);
//        bundle.putIntArray("POSITIONS CHECKED", posChecked);
//        for (int i = 0; i < numChecked; ++i) {
//            bundle.putDoubleArray("DATA " + i, data[i]);
//            bundle.putDoubleArray("DATA RAW " + i, dataRaw[i]);
//            bundle.putParcelableArray("FFT DATA " + i, fft[i]);
//            bundle.putDoubleArray("ABS_FFT DATA " + i, absFFT[i]);
//        }

        TimeFragment fragment = new TimeFragment();
//        fragment.setArguments(bundle);
        loadFragment(fragment);

        BottomNavigationView navigation = findViewById(R.id.tab_view);
        navigation.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment1;
            switch (item.getItemId()) {
                case R.id.tab_time:
                    fragment1 = new TimeFragment();
//                    fragment1.setArguments(bundle);
                    loadFragment(fragment1);
                    return true;
                case R.id.tab_freq:
                    fragment1 = new FreqFragment();
//                    fragment1.setArguments(bundle);
                    loadFragment(fragment1);
                    return true;
                case R.id.tab_info:
                    fragment1 = new InfoFragment();
//                    fragment1.setArguments(bundle);
                    loadFragment(fragment1);
                    return true;
            }
            return false;
        });
    }

    private void getData(@NonNull Intent intent) {
        numChecked = intent.getIntExtra("NO. CHECK", -1);
        files = intent.getStringArrayExtra("FILES DATA NAME");
        posChecked = intent.getIntArrayExtra("POSITIONS CHECKED");
        Log.i("Num check (TabView)", String.valueOf(numChecked));
        data = new double[numChecked][];
        dataRaw = new double[numChecked][];
        fft = new Complex[numChecked][];
        absFFT = new double[numChecked][];
        for (int i = 0; i < numChecked; ++i) {
            data[i] = intent.getDoubleArrayExtra("DATA " + i);
            dataRaw[i] = intent.getDoubleArrayExtra("DATA RAW " + i);


//            fft[i] = FFT.fft(Helper.appendZeros(dataRaw[i]));
//            absFFT[i] = FFT.absFFT(fft[i]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(TabView.this, MainActivity.class);
            startActivity(intent);  // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
