package fuzzy.quantifier;

import fuzzy.Label;

public class RelativeQuantifier extends Quantifier {
    public RelativeQuantifier(String name, Label label) {
        super(name, label);
    }

    @Override
    public double getMembership(double value) {
        return 0.0;
    }
}
