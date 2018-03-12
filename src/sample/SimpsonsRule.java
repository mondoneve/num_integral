package sample;

public class SimpsonsRule extends Method {
    @Override
    public double integrate(Function func, double aBound, double bBound, int numOfPartitions) {
        double h = (bBound - aBound) / (double)numOfPartitions;
        double result = 0;
        double x = aBound;
        while (x+h<=bBound) {
            result += (func.getValue(x)+func.getValue(x+0.5*h)+func.getValue(x+h))*h/3;
            x+=h;
        }
        return  result;
    }
}
