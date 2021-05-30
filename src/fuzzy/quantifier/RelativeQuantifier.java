package fuzzy.quantifier;

import fuzzy.Label;

public class RelativeQuantifier extends Quantifier {
    public RelativeQuantifier(String name, Label label) {
        super(name, label);
    }

    @Override
    public double getSupportCardinality() {
        return label.getMembershipFunction().getSupportCardinality();
    }

    @Override
    public double getCardinality() {
       return label.getMembershipFunction().getCardinality();
    }

    @Override
    public double getMembership(double value) {
        return label.getMembership(value);
    }
}
