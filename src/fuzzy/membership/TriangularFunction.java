package fuzzy.membership;

public class TriangularFunction extends MembershipFunction {
    private double left;
    private double apex;
    private double right;

    public TriangularFunction(double left, double apex, double right) {
        this.left = left;
        this.apex = apex;
        this.right = right;
    }

    @Override
    public double getMembership(double value) {
        if (value <= left || value >= right) {
            return 0.0;
        } else if (value < apex) {
            return (value - left) / (apex - left);
        } else if (value > apex) {
            return (right - value) / (right - apex);
        } else {
            return 1.0;
        }
    }

    @Override
    public String toString() {
        return "TriangularFunction{" +
                "left=" + left +
                ", apex=" + apex +
                ", right=" + right +
                '}';
    }
}
