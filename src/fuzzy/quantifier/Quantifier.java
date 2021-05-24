package fuzzy.quantifier;

import fuzzy.Label;

import java.util.List;

public abstract class Quantifier {
    private String name;
    private Label label;

    public Quantifier(String name, Label label) {
        this.name = name;
        this.label = label;
    }

    public abstract double getMembership(double value);
}
