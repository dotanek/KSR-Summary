package fuzzy;

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

    public Label(String name, Universe universe, MembershipFunction membershipFunction, LinguisticVariable linguisticVariable) {
        this.name = name;
        this.universe = universe;
        this.membershipFunction = membershipFunction;
        this.linguisticVariable = linguisticVariable;
    }

    public Label(String name, Universe universe, MembershipFunction membershipFunction) {
        this.name = name;
        this.universe = universe;
        this.membershipFunction = membershipFunction;
    }

    public void setLinguisticVariable(LinguisticVariable linguisticVariable) {
        this.linguisticVariable = linguisticVariable;
    }

    public LinguisticVariable getLinguisticVariable() {
        return linguisticVariable;
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
        /*List<Double> cut = new ArrayList<>();
        for (double i = universe.getLeft(); i <= universe.getRight(); i += universe.getStep()) {
            if (membershipFunction.getMembership(i) > threshold) {
                cut.add(i);
            }
        }
        return cut;*/
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

    public List<Double> getComplement() {
        return null;
        /*List<Double> complement = new ArrayList<>();
        for (double i = universe.getLeft(); i <= universe.getRight(); i += universe.getStep()) {
            if (membershipFunction.getMembership(i) == 0.0) {
                complement.add(i);
            }
        }
        return complement;*/
    }

    public boolean isEmpty() {
        return getHeight() == 0.0;
    }

    public boolean isConvex() {
        return true;
    }

    public boolean isNormal() {
        return getHeight() == 1.0;
    }

    public static double getIntersectionMembership(double value, Label... sets) {
        double minMembership = 1.0;

        for (Label set : sets) {
            double currentMembership = set.getMembership(value);
            if (minMembership < currentMembership) {
                minMembership = currentMembership;
            }
        }

        return minMembership;
    }

    public static List<Double> intersection(Label l1, Label l2) {
        return null;
    }

    public static List<Double> difference(Label l1, Label l2) {
        return null;
    }

    public static List<Double> union(Label l1, Label l2) {
        return null;
    }
}
