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
        try {
            if (value <= leftBottom || value >= rightBottom) {
                return 0.0;
            } else if (value <= leftTop) {
                return (value - leftBottom) / (leftTop - leftBottom);
            } else if (value >= rightTop) {
                return (rightBottom - value) / (rightBottom - rightTop);
            } else {
                return 1.0;
            }
        } catch (ArithmeticException e) { // Division by 0 therefore trapezoid has a straight angle and should return 1.
            return 1.0;
        }
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
