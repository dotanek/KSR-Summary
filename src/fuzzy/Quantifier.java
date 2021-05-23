package fuzzy;

import java.util.List;

public abstract class Quantifier {
    private String name;
    private List<Label> labels;

    public abstract double getLabel(double value);
}
