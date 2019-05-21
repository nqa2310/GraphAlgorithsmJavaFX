package util;

public class Equation2 {
    double x1;
    double x2;

    public Equation2() {

    }

    public void solution(double a, double b, double c) {
        if (a == 0) {
            if (b == 0) {
            } else {
                this.x1 = this.x2 = -c / b;
            }
            return;
        }
        double delta = b*b - 4*a*c;
        double x1;
        double x2;
        if (delta >= 0) {
            x1 = (float) ((-b + Math.sqrt(delta)) / (2*a));
            x2 = (float) ((-b - Math.sqrt(delta)) / (2*a));
            this.x1 = x1;
            this.x2 = x2;
        } else {
        }
    }

    public static void main(String[] args) {
        Equation2 e = new Equation2();
        e.solution(1,-2,1);
        System.out.println(e.x1);
    }
}
