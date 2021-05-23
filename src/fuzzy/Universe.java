package fuzzy;

import java.util.ArrayList;
import java.util.List;

public class Universe {
    private List<Double> values;

    public Universe(List<Double> values) {
        this.values = values;
    }

    public Universe(double left, double right, double step) {
        values = new ArrayList<Double>();

        for (double i = left; i <= right; i += step) {
            values.add(i);
        }
    }

    public List<Double> getValues() {
        return values;
    }
}
