package fuzzy;

import fuzzy.membership.GaussianFunction;
import fuzzy.membership.MembershipFunction;
import fuzzy.universe.Universe;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class Label {
    private String name;
    private Universe universe;
    private MembershipFunction membershipFunction;
    private LinguisticVariable linguisticVariable;
    
    public Label(String name, Universe universe, MembershipFunction membershipFunction) {
        this.name = name;
        this.universe = universe;
        this.membershipFunction = membershipFunction;
    }

    public void setLinguisticVariable(LinguisticVariable linguisticVariable) {
        this.linguisticVariable = linguisticVariable;
    }

    public String getName() {
        return name;
    }

    public double getMembership(double value) {
        return membershipFunction.getMembership(value);
    }

    public double getMembership(Subject subject) {
        double value = subject.getAttribute(linguisticVariable.getName());
        return membershipFunction.getMembership(value);
    }

    public List<Double> getSupport() {
        return getAlphaCut(0.0);
    }

    public List<Double> getAlphaCut(double threshold) {
        return null;
    }

    public double getHeight() {
        double max = 0.0;
        for (double i = universe.getLeft(); i <= universe.getRight(); i += universe.getStep()) {
            double membership = membershipFunction.getMembership(i);
            if (membership > max) {
                max = membership;
            }
        }
        return max;
    }

    public double getFuzziness() {
        if (membershipFunction instanceof GaussianFunction) {
            return  1.0;
        }
        return membershipFunction.getSupportCardinality() / (universe.getRight() - universe.getLeft());
    }

    public double getCardinality() {
        double left = universe.getLeft();
        double right = universe.getRight();

        if (membershipFunction instanceof GaussianFunction) {
            double step = universe.getStep();
            double cardinality = 0.0;

            for (double i = left; i <= right; i += step) {
                cardinality += i * step;
            }

            return cardinality / (right - left);
        }
        return membershipFunction.getCardinality() / (right - left);
    }

    public MembershipFunction getMembershipFunction() {
        return membershipFunction;
    }

    public List<Double> getComplement() { return null; }

    public boolean isEmpty() {
        return getHeight() == 0.0;
    }

    public boolean isConvex() {
        return true;
    }

    public boolean isNormal() {
        return getHeight() == 1.0;
    }
}
