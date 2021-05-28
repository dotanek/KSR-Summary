package fuzzy.universe;

import java.util.ArrayList;
import java.util.List;

public class Universe {
    private double left;
    private double right;
    private double step;

    public Universe(double left, double right, double step) {
        this.left = left;
        this.right = right;
        this.step = step;
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getStep() {
        return step;
    }
}
