package com.ktpm.fileprocessing;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.ktpm.fileprocessing.SignalProcessing.Detrend;

import org.apache.commons.io.comparator.LastModifiedFileComparator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 100;
    private ArrayList<String> myList;
    private ListView lvTextFilesList;


    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTextFilesList = findViewById(R.id.lv_text_files_list);
        Button btnGetItemsChecked = findViewById(R.id.btn_get_item_selected);

        myList = new ArrayList<>();
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            ArrayAdapter<String> adapter;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermission()) {
                    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/EMG_Data/");
                    if (dir.exists()) {
                        Log.d("PATH", dir.toString());
                        File[] list = dir.listFiles();
                        Arrays.sort(list, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                        int i = 0;
                        while (i < list.length) {
                            myList.add(list[i].getName());
                            ++i;
                        }
                        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, myList);
                        lvTextFilesList.setAdapter(adapter);
                        lvTextFilesList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);

                    }
                } else {
                    requestPermission();
                }
            } else {
                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/EMG_Data/");

                if (dir.exists()) {
                    Log.d("PATH", dir.toString());
                    File[] list = dir.listFiles();

                    int i = 0;
                    while (i < list.length) {
                        myList.add(list[i].getName());
                        ++i;
                    }
                    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, myList);
                    lvTextFilesList.setAdapter(adapter);
                    lvTextFilesList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
                }
            }


        }

        FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
        int count = fm.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fm.popBackStack();
        }

        lvTextFilesList.setOnItemClickListener((parent, view, position, id) -> {
            int checkedItemCount = lvTextFilesList.getCheckedItemCount();
            Log.d("Item Count", String.valueOf(checkedItemCount));
            if (checkedItemCount > 2) {
                lvTextFilesList.setItemChecked(position, false);
            }
        });

        btnGetItemsChecked.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TabView.class);
            SparseBooleanArray sparseBooleanArray = lvTextFilesList.getCheckedItemPositions();
            Log.i("List View Multi Choice", "Total Number Selected(sp): " + sparseBooleanArray.size());


            int numChecked = sparseBooleanArray.size();
            String[] files = new String[numChecked];
            double[][] data = new double[numChecked][];
            double[][] dataRaw = new double[numChecked][];
            int[] posChecked = new int[numChecked];


            for (int i = 0; i < numChecked; ++i) {
                int position = sparseBooleanArray.keyAt(i);
                posChecked[i] = position;
                files[i] = myList.get(position);
                Log.i("Name File", myList.get(position));
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/EMG_Data/" + myList.get(position));
                ArrayList<Double> tempData = readFile(file);

                int lengthData = tempData.size();
                dataRaw[i] = new double[lengthData];
                double[] timeData = new double[lengthData];
                double[] dataRemoveDC;

                Log.i("LENGTH FILE", String.valueOf(lengthData));
                for (int j = 0; j < lengthData; ++j) {
                    dataRaw[i][j] = tempData.get(j);
                    timeData[j] = tempData.get(j) / 101;
                }
                dataRemoveDC = Detrend.detrend(timeData);

                data[i] = new double[lengthData];

                System.arraycopy(dataRemoveDC, 0, data[i], 0, lengthData);
            }

            intent.putExtra("NO. CHECK", numChecked);
            intent.putExtra("FILES DATA NAME", files);
            intent.putExtra("POSITIONS CHECKED", posChecked);
            for (int i = 0; i < numChecked; ++i) {
                intent.putExtra("DATA " + i, data[i]);
                intent.putExtra("DATA RAW " + i, dataRaw[i]);
            }

            sparseBooleanArray.clear();
            startActivity(intent);
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Write External Storage permission allows us to read  files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                    {android.Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted, Now you can use local drive.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied, You cannot use local drive.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @NonNull
    private ArrayList<Double> readFile(File file) {
        String line;
        ArrayList<Double> lines = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            while ((line = bf.readLine()) != null) {
                if (isNumeric(line)) {
                    lines.add(Double.parseDouble(line));
                }
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    private boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        return pattern.matcher(s).matches();
    }
}