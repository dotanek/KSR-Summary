package fuzzy.universe;

import java.util.ArrayList;
import java.util.List;

public class DenseUniverse extends Universe {
    private double left;
    private double right;
    private double step;

    public DenseUniverse(double left, double right, double step) {
        this.left = left;
        this.right = right;
        this.step = step;
    }

    @Override
    public List<Double> getValues() {
        List<Double> values = new ArrayList<>();
        for (double i = left; i <= right; i += step) {
            values.add(i);
        }

        return values;
    }
}
