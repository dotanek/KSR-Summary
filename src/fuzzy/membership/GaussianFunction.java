package fuzzy.membership;

public class GaussianFunction extends MembershipFunction {

    private double apex;
    private double spread;

    public GaussianFunction(double apex, double spread) {
        this.apex = apex;
        this.spread = spread;
    }

    @Override
    public double getMembership(double value) {
        return (Math.exp(-Math.pow((value - apex)/spread,2)/2));
    }

    @Override
    public double getSupportCardinality() {
        return 0.0;
    }

    @Override
    public double getCardinality() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "GaussianFunction{" +
                "apex=" + apex +
                ", spread=" + spread +
                '}';
    }
}
