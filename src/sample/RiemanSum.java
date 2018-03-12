package sample;

public class RiemanSum extends Method {
    @Override
    public double integrate(Function func, double aBound, double bBound, int numOfPartitions) {
        double h = (bBound - aBound) / (double)numOfPartitions;
        double result = 0;
        double x = aBound;
        while (x+h<=bBound) {
            result += func.getValue(x+h*0.5)*h;
            x+=h;
        }
        return  result;
    }
}
