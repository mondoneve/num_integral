package sample;

public class OscFunction1 extends Function {

    @Override
    public double getValue(double x) {
        return Math.cos(10*x)+Math.sin(x)+1.0/x;
    }
}
