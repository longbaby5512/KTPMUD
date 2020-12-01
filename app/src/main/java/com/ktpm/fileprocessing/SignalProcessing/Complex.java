package com.ktpm.fileprocessing.SignalProcessing;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.Contract;

import java.util.Objects;

public class Complex implements Parcelable {
    public static final Creator<Complex> CREATOR = new Creator<Complex>() {
        @NonNull
        @Contract("_ -> new")
        @Override
        public Complex createFromParcel(Parcel in) {
            return new Complex(in);
        }

        @NonNull
        @Contract(value = "_ -> new", pure = true)
        @Override
        public Complex[] newArray(int size) {
            return new Complex[size];
        }
    };
    public final double real;
    public final double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    protected Complex(@NonNull Parcel in) {
        real = in.readDouble();
        imaginary = in.readDouble();
    }

    @NonNull
    @Contract(pure = true)
    public static Complex plus(@NonNull Complex a, @NonNull Complex b) {
        double real = a.real + b.real;
        double imag = a.imaginary + b.imaginary;
        return new Complex(real, imag);
    }

    @NonNull
    public String toString() {
        if (imaginary == 0) return real + "";
        if (real == 0) return imaginary + "i";
        if (imaginary < 0) return real + " - " + (-imaginary) + "i";
        return real + " + " + imaginary + "i";
    }

    public double abs() {
        return Math.hypot(real, imaginary);
    }

    public double phase() {
        return Math.atan2(imaginary, real);
    }

    public Complex plus(@NonNull Complex b) {
        return new Complex(this.real + b.real, this.imaginary + b.imaginary);
    }

    public Complex minus(@NonNull Complex b) {
        return new Complex(this.real - b.real, this.imaginary - b.imaginary);
    }

    public Complex times(@NonNull Complex b) {
        double real = this.real * b.real - this.imaginary * b.imaginary;
        double imaginary = this.real * b.imaginary + this.imaginary * b.real;
        return new Complex(real, imaginary);
    }

    public Complex scale(double alpha) {
        return new Complex(alpha * real, alpha * imaginary);
    }

    public Complex conjugate() {
        return new Complex(real, -imaginary);
    }

    public Complex reciprocal() {
        double scale = real * real + imaginary * imaginary;
        return new Complex(real / scale, -imaginary / scale);
    }

    public Complex divides(@NonNull Complex b) {
        return this.times(b.reciprocal());
    }

    public Complex exp() {
        return new Complex(Math.exp(real) * Math.cos(imaginary), Math.exp(real) * Math.sin(imaginary));
    }

    public Complex sin() {
        return new Complex(Math.sin(real) * Math.cosh(imaginary), Math.cos(real) * Math.sinh(imaginary));
    }

    public Complex cos() {
        return new Complex(Math.cos(real) * Math.cosh(imaginary), -Math.sin(real) * Math.sinh(imaginary));
    }

    public Complex tan() {
        return sin().divides(cos());
    }

    public boolean equals(Object x) {
        if (x == null) return false;
        if (this.getClass() != x.getClass()) return false;
        Complex that = (Complex) x;
        return (this.real == that.real) && (this.imaginary == that.imaginary);
    }

    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(real);
        dest.writeDouble(imaginary);
    }
}