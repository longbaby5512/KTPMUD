package com.ktpm.fileprocessing.SignalProcessing;

import android.support.annotation.NonNull;

public class FFT {

    // compute the FFT of data[], assuming its length n is a power of 2
    @NonNull
    public static Complex[] fft(@NonNull double[] data) {
        int length = data.length;

        // base case
        if (length == 1)
            return new Complex[]{new Complex(data[0], 0)};

        // radix 2 Cooley-Tukey FFT
        if (length % 2 != 0) {
            throw new IllegalArgumentException("length is not a power of 2");
        }

        // compute FFT of even terms
        double[] even = new double[length / 2];
        for (int k = 0; k < length / 2; k++) {
            even[k] = data[2 * k];
        }
        Complex[] evenFFT = fft(even);

        // compute FFT of odd terms
        double[] odd = new double[length / 2]; // reuse the array (to avoid length log length space)
        for (int k = 0; k < length / 2; k++) {
            odd[k] = data[2 * k + 1];
        }
        Complex[] oddFFT = fft(odd);

        // combine
        Complex[] fft = new Complex[length];
        for (int k = 0; k < length / 2; k++) {
            double kth = -2 * k * Math.PI / length;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            fft[k] = evenFFT[k].plus(wk.times(oddFFT[k]));
            fft[k + length / 2] = evenFFT[k].minus(wk.times(oddFFT[k]));
        }
        return fft;
    }

    @NonNull
    public static double[] absFFT(@NonNull Complex[] fft) {
        double[] absFFT = new double[fft.length];
        for (int i = 0; i < fft.length; ++i) {
            absFFT[i] = fft[i].abs();
        }
        return absFFT;
    }

}