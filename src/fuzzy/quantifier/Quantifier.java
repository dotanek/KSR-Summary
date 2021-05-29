package fuzzy.quantifier;

import fuzzy.Label;

import java.util.List;

public abstract class Quantifier {
    private String name;
    protected Label label;

    public Quantifier(String name, Label label) {
        this.name = name;
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public abstract double getMembership(double value);
}
