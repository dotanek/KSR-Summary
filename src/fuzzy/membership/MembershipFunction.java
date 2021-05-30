package fuzzy.membership;

public abstract class MembershipFunction {
    public abstract double getMembership(double value);

    public abstract double getSupportCardinality();
    public abstract double getCardinality();

    public abstract String toString();
}
