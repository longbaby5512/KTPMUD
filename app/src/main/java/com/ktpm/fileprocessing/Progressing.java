package com.ktpm.fileprocessing;

import android.support.annotation.NonNull;
import android.util.Log;

import com.jjoe64.graphview.GraphView;

import java.util.Arrays;

public class Progressing {
    private static double sum(@NonNull double[] arr) {
        double result = 0;
        for (double it : arr) {
            result += it;
        }
        return result;
    }

    public static double mean(@NonNull double[] arr) {
        Log.i("AVER", String.valueOf(sum(arr) / arr.length));
        return sum(arr) / arr.length;
    }

    private static double variance(@NonNull double[] arr) {
        double result = 0;
        double aver = mean(arr);
        for (double it : arr) {
            result += (it - aver) * (it - aver);
        }
        Log.i("VAR", String.valueOf(result / arr.length));
        return result / arr.length;
    }

    public static double getMax(@NonNull double[] arr) {
        double max = arr[0];
        for (double it : arr) {
            if (it > max) max = it;
        }
        return max;
    }

    public static double getMin(@NonNull double[] arr) {
        double min = arr[0];
        for (double it : arr) {
            if (it < min) min = it;
        }
        return min;
    }

    public static double SNR(double[] arr) {
        return 20 * Math.log10(mean(arr) / Math.sqrt(variance(arr)));
    }

    public static double median(@NonNull double[] arr) {
        double[] tmpArr = new double[arr.length];
        System.arraycopy(arr, 0, tmpArr, 0, arr.length);
        Arrays.sort(tmpArr);
        if (arr.length % 2 == 0) {
            return (tmpArr[arr.length / 2] + tmpArr[arr.length / 2]) / 2;
        }
        return tmpArr[arr.length / 2];
    }

    public static void drawGraph(@NonNull GraphView graph, double minY, double maxY, double maxX) {
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setMaxX(maxX);
        graph.getViewport().setMinY(minY);
        graph.getViewport().setMaxY(maxY);
        graph.getGridLabelRenderer().setNumVerticalLabels(10);
        graph.getGridLabelRenderer().setNumHorizontalLabels(10);
    }
}
