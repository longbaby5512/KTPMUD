package com.ktpm.fileprocessing.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.ktpm.fileprocessing.Progressing;
import com.ktpm.fileprocessing.R;

import java.util.ArrayList;
import java.util.Random;

public class TimeFragment extends Fragment {
    private final ArrayList<Integer> color = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_time, container, false);
//        GraphView time_graph = view.findViewById(R.id.time_chart);
//
//        // Get Data
//        int numChecked = getArguments().getInt("NO. CHECK");
//        double[][] data = new double[numChecked][];
//        int[] posChecked = getArguments().getIntArray("POSITIONS CHECKED");
//        for(int i = 0; i < numChecked; ++i) {
//            data[i] = getArguments().getDoubleArray("DATA " + i);
//        }
//
//        for (int i = 0; i < numChecked; ++i) {
//            Log.i("CHECK", String.valueOf(i));
//            double[] amplitude = new double[data[i].length];
//            System.arraycopy(data[i], 0, amplitude, 0, data[i].length);
//
//            Random rnd = new Random();
//            LineGraphSeries<DataPoint> timeSeries = new LineGraphSeries<>();
//            color.add(Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256)));
//            timeSeries.setColor(color.get(i));
//            timeSeries.setThickness(2);
//            timeSeries.setTitle("File " + posChecked[i]);
//            for (int k = 0; k < data[i].length; k++) {
//                timeSeries.appendData(new DataPoint(k, amplitude[k]), true, data[i].length);
//            }
//            time_graph.addSeries(timeSeries);
//            time_graph.getViewport().setMaxX(data[i].length / 3.0);
//
//            //min,max time signal
//            double minTimeSignal = Progressing.getMin(amplitude);
//            double maxTimeSignal = Progressing.getMax(amplitude);
//
//            //draw time graph
//            Log.i("DRAW", "Draw graph " + posChecked[i]);
//            Progressing.drawGraph(time_graph, minTimeSignal, maxTimeSignal, data[i].length / 3.0);
//        }
//        time_graph.getLegendRenderer().setVisible(true);
//        time_graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        return view;
    }



}