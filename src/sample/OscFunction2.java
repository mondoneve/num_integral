package sample;

public class OscFunction2 extends Function{

    @Override
    public double getValue(double x) {
        return 1.0/x+Math.sin(x)+Math.cos(100*x);
    }
}
