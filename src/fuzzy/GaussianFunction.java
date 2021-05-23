package fuzzy;

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
}
