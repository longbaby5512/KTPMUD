package com.ktpm.fileprocessing.SignalProcessing;

import android.support.annotation.NonNull;

import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Detrend {

    @NonNull
    public static double[] detrend(@NonNull double[] x) {

        double[][] newInput = new double[x.length][2];
        for (int i = 0; i < x.length; i++) {
            newInput[i][1] = x[i];
            newInput[i][0] = i;
        }

        SimpleRegression regression = new SimpleRegression();
        regression.addData(newInput);
        double slope = regression.getSlope();
        double intercept = regression.getIntercept();
        double[][] output = new double[newInput.length][2];
        double[] y = new double[x.length];
        int j = 0;
        for (int i = 0; i < newInput.length; i++) {
            output[i][1] = newInput[i][1] - slope * newInput[i][0] - intercept;
            y[j++] = output[i][1];
            output[i][0] = newInput[i][0];
        }
        return y;
    }

}
