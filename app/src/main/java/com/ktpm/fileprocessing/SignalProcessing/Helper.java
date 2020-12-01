package com.ktpm.fileprocessing.SignalProcessing;

import android.support.annotation.NonNull;

public class Helper {
    public static boolean check(int n) {
        if (n <= 0)
            return false;
        while (n > 1) {
            if (n % 2 != 0)
                return false;
            n = n / 2;
        }
        return true;
    }

    public static int sampleNumbers(int n) {
        double pow = Math.log(n) / Math.log(2);
        return (int) Math.pow(2, Math.ceil(pow));
    }

    @NonNull
    public static double[] appendZeros(@NonNull double[] a) {
        int n = a.length;
        if (check(n)) {
            return a;
        } else {
            int m = sampleNumbers(n);
            double[] b = new double[m];
            System.arraycopy(a, 0, b, 0, n);
            for (int j = n; j < m; j++) {
                b[j] = 0.0;
            }
            return b;
        }
    }
}
