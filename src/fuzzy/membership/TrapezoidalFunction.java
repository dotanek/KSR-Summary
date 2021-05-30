package fuzzy.membership;

public class TrapezoidalFunction extends MembershipFunction {

    private double leftBottom;
    private double leftTop;
    private double rightTop;
    private double rightBottom;

    public TrapezoidalFunction(double leftBottom, double leftTop, double rightTop, double rightBottom) {
        this.leftBottom = leftBottom;
        this.leftTop = leftTop;
        this.rightTop = rightTop;
        this.rightBottom = rightBottom;
    }

    @Override
    public double getMembership(double value) {
        if (leftBottom == leftTop || rightBottom == rightTop) { // Avoiding division by 0 in case trapezoid has straight angle.
            if (value == leftBottom) {
                return 1.0;
            } else if (value == rightBottom) {
                return 1.0;
            }
        }

        if (value < leftBottom || value > rightBottom) {
            return 0.0;
        } else if (value <= leftTop) {
            return (value - leftBottom) / (leftTop - leftBottom);
        } else if (value >= rightTop) {
            return (rightBottom - value) / (rightBottom - rightTop);
        } else {
            return 1.0;
        }
    }

    @Override
    public double getSupportCardinality() {
        return rightBottom - leftBottom;
    }

    @Override
    public double getCardinality() {
        return 0.5 * (rightBottom - leftBottom + (rightTop - leftTop));
    }

    @Override
    public String toString() {
        return "TrapezoidalFunction{" +
                "leftBottom=" + leftBottom +
                ", leftTop=" + leftTop +
                ", rightTop=" + rightTop +
                ", rightBottom=" + rightBottom +
                '}';
    }
}
