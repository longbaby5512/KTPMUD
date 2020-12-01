package com.ktpm.fileprocessing.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ktpm.fileprocessing.Progressing;
import com.ktpm.fileprocessing.R;


public class InfoFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
//        TextView tvText = view.findViewById(R.id.text_info);
//
//
//        int numChecked = getArguments().getInt("NO. CHECK");
//        Log.i("Num check", String.valueOf(numChecked));
//
//        int[] posChecked = getArguments().getIntArray("POSITIONS CHECKED");
//        tvText.setText("");
//        for (int i = 0; i < numChecked; ++i) {
//            tvText.append("File " + posChecked[i] + ":\n");
//
//            double[] dataRaw = getArguments().getDoubleArray("DATA " + i);
////            double[] absFFT = getArguments().getDoubleArray("ABS_FFT DATA " + i);
//
//            tvText.append("\t +) SNR: " + Progressing.SNR(dataRaw) + " dB\n");
//            tvText.append("\t +) Mean: " + Progressing.mean(dataRaw) + "\n");
//            tvText.append("\t +) Max: " + Progressing.getMax(dataRaw) + "\n");
//            tvText.append("\t +) Min: " + Progressing.getMin(dataRaw) + "\n");
//            tvText.append("\t +) Median: " + Progressing.median(dataRaw) + "\n");
////            tvText.append("\t +) Median frequency: " + Progressing.median(absFFT) + "\n");
//        }
        return view;
    }
}