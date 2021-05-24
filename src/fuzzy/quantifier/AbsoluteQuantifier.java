package fuzzy.quantifier;

import fuzzy.Label;

public class AbsoluteQuantifier extends Quantifier {

    public AbsoluteQuantifier(String name, Label label) {
        super(name, label);
    }

    @Override
    public double getMembership(double value) {
        return 0.0;
    }
}
