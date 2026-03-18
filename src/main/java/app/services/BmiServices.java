package app.services;

public class BmiServices {

    public static String getBMIVerdict(double bmi) {
        if (bmi <= 18.5)
            return "Undervægtig";
        if (bmi > 18.5 && bmi <= 24.9)
            return "Normalvægtig";
        if (bmi > 24.9 && bmi <= 29.9)
            return "Overvægt";
        return "Fedme";
    }

    public static double getBMI(int weight, int height){
        return weight / Math.pow(height / 100.0, 2);
    }
}
