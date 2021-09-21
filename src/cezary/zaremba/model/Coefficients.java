package cezary.zaremba.model;

public class Coefficients {
    private double k;
    private double alfa;

    public double getAlfa() {
        return alfa;
    }

    public Coefficients(double k, double alfa) {
        this.k = k;
        this.alfa = alfa;
    }

    public void setAlfa(double alfa) {
        this.alfa = alfa;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }
}
