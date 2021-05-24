package fuzzy.universe;

import java.util.List;

public class DiscretteUniverse extends Universe {
    private List<Double> values;

    public DiscretteUniverse(List<Double> values) {
        this.values = values;
    }

    public DiscretteUniverse(double lef, double right, double step) {
        this.values = values;
    }

    @Override
    public List<Double> getValues() {
        return values;
    }
}
