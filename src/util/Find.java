package util;

public class Find {
    double a;
    double b;
    public double X;
    public double Y;
    public Find() {

    }
    public void findLine(double x1, double y1, double x2, double y2)
    {
        this.a = (y1-y2)/(x1-x2);
        this.b = (x1*y2-x2*y1)/(x1-x2);
    }
    public double lengthLine(double x1, double y1, double x2, double y2) {
        double a = x1 - x2;
        double b = y1 - y2;
        return Math.sqrt(a*a+b*b);
    }
    public void findCoordinates(double x1, double y1, double x2, double y2, double rad) {
        Equation2 e = new Equation2();
        e.solution(a*a+1,2*a*(b-y1)-2*x1,x1*x1+(b-y1)*(b-y1)-rad*rad);
        if (e.x1 == e.x2) {
            this.X = e.x1;
            this.Y = e.x1*a + b;
        }
        else {
            double X = e.x1;
            double Y = e.x1*a + b;
            if (lengthLine(X,Y,x2,y2)>lengthLine(x1,y1,x2,y2)) {
                this.X = e.x2;
                this.Y = e.x2*a + b;
            }
            else {
                this.X = X;
                this.Y = Y;
            }

        }
    }
}
