package cezary.zaremba.calculation;

import cezary.zaremba.model.Coefficients;

public class CoefficientsCalculation {

    private double calculateKh(double freq){
        double[] a = {-5.33980, -0.35351, -0.23789, -0.94158};
        double[] b = {-0.10008, 1.26970, 0.86036, 0.64552};
        double[] c = {1.13098, 0.45400, 0.15354, 0.16817};
        double m = -0.18961;
        double ck = 0.71147;
        double sum = 0;
        double tmp;
        for (int i = 0; i < 4; i++){
            tmp = (a[i]*Math.exp(-(Math.pow(((Math.log10(freq) - b[i])/c[i]), 2))));
            sum += tmp;
        }
        sum += m*Math.log10(freq)+ck;

        return Math.pow(10,sum);

    }

    private double calculateKv(double freq){
        double[] a = {-3.80595, -3.44965, -0.39902, 0.50167};
        double[] b = {0.56934, -0.22911, 0.73042, 1.07319};
        double[] c = {0.81061, 0.51059, 0.11899, 0.27195};
        double m = -0.16398;
        double ck = 0.63297;
        double sum = 0;
        double tmp;
        for (int i = 0; i < 4; i++){
            tmp = (a[i]*Math.exp(-(Math.pow(((Math.log10(freq) - b[i])/c[i]), 2))));
            sum += tmp;
        }
        sum += m*Math.log10(freq)+ck;

        return Math.pow(10,sum);
    }

    private double calculateAlfah(double freq){
        double[] a = {-0.14318, 0.29591, 0.32177, -5.37610, 16.1721};
        double[] b = {1.82442, 0.77564, 0.63773, -0.96230, -3.29980};
        double[] c = {-0.55187, 0.19822, 0.13164, 1.47828, 3.43990};
        double m = 0.67849;
        double ck = -1.95537;
        double sum = 0;
        double tmp;
        for (int i = 0; i < 5; i++){
            tmp = (a[i]*Math.exp(-(Math.pow(((Math.log10(freq) - b[i])/c[i]), 2))));
            sum += tmp;
        }
        sum += m*Math.log10(freq)+ck;

        return sum;
    }

    private double calculateAlfav(double freq){
        double[] a = {-0.07771, 0.56727, -0.20238, -48.2991, 48.5833};
        double[] b = {2.33840, 0.95545, 1.14520, 0.791669, 0.791459};
        double[] c = {-0.76284, 0.54039, 0.26809, 0.116226, 0.116479};
        double m = -0.053739;
        double ck = 0.83433;
        double sum = 0;
        double tmp;
        for (int i = 0; i < 5; i++){
            tmp = (a[i]*Math.exp(-(Math.pow(((Math.log10(freq) - b[i])/c[i]), 2))));
            sum += tmp;
        }
        sum += m*Math.log10(freq)+ck;

        return sum;
    }

    public Coefficients calculateCoefficients(double frequency, double pathElevationAngle, double polarizationTiltAngle){
        double kh = calculateKh(frequency);
        double kv = calculateKv(frequency);
        double k = (kh + kv + (kh - kv) * (Math.cos(0.0174532925*pathElevationAngle)) * Math.cos(2 * (0.0174532925*polarizationTiltAngle)))/2.0;
        double ah = calculateAlfah(frequency);
        double av = calculateAlfav(frequency);
        double a = ((kh*ah+kv*av+(kh*ah-kv*av)*(Math.pow(Math.cos(0.0174532925*pathElevationAngle), 2)) * Math.cos(2 * (0.0174532925*polarizationTiltAngle)))/2*k);

        return new Coefficients(k,a);

    }



}
