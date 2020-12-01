package com.ktpm.fileprocessing.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.ktpm.fileprocessing.Progressing;
import com.ktpm.fileprocessing.R;
import com.ktpm.fileprocessing.SignalProcessing.Complex;
import com.ktpm.fileprocessing.SignalProcessing.FFT;
import com.ktpm.fileprocessing.SignalProcessing.Helper;
import com.ktpm.fileprocessing.TabView;

import java.util.ArrayList;
import java.util.Random;

public class FreqFragment extends Fragment {
    private final ArrayList<Integer> color = new ArrayList<>();
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_freq, container, false);
        GraphView frequency_graph = view.findViewById(R.id.frequency_chart);

        // Get Data
//        int numChecked = getArguments().getInt("NO. CHECK");
//        int[] posChecked = getArguments().getIntArray("POSITIONS CHECKED");
//
//        for (int i = 0; i < numChecked; ++i) {
//            Random rnd = new Random();
//            LineGraphSeries<DataPoint> fftSeries = new LineGraphSeries<>();
//            color.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
//            fftSeries.setColor(color.get(i));
//            fftSeries.setThickness(2);
//            fftSeries.setTitle("File" + posChecked[i]);
//
//            double[] absFFT = getArguments().getDoubleArray("ABS_FFT DATA " + i);
//            for (int k = 0; k < absFFT.length; k++) {
//                double fs = 1000;
//                fftSeries.appendData(new DataPoint(k * fs / absFFT.length, absFFT[k]), true, absFFT.length);
//            }
//            frequency_graph.addSeries(fftSeries);
//            frequency_graph.getViewport().setMaxX(absFFT.length / 3.0);
//
//            //min, max frequency
//            double minFFTSignal = Progressing.getMin(absFFT);
//            double maxFFTSignal = Progressing.getMax(absFFT);
//
//            Progressing.drawGraph(frequency_graph, minFFTSignal , maxFFTSignal, 500);
//        }
//        frequency_graph.getLegendRenderer().setVisible(true);
//        frequency_graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        return view;
    }
}