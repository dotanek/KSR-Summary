package fuzzy;

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
        if (value <= leftBottom || value >= rightBottom) {
            return 0.0;
        } else if (value <= leftTop) {
            return (value - leftBottom) / (leftTop - leftBottom);
        } else if (value >= rightTop) {
            return (rightBottom - value) / (rightBottom - rightTop);
        } else {
            return 1.0;
        }
    }
}
