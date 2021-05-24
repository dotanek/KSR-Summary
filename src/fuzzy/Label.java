package fuzzy;

import fuzzy.membership.MembershipFunction;
import fuzzy.universe.Universe;

import java.util.ArrayList;
import java.util.List;

public class Label {
    private String name;
    private Universe universe;
    private MembershipFunction membershipFunction;

    public Label(String name, Universe universe, MembershipFunction membershipFunction) {
        this.name = name;
        this.universe = universe;
        this.membershipFunction = membershipFunction;
    }

    public double getMembership(double value) {
        return membershipFunction.getMembership(value);
    }

    public List<Double> getSupport() {
        return getAlphaCut(0.0);
    }

    public List<Double> getAlphaCut(double threshold) {
        List<Double> cut = new ArrayList<>();
        for (double value : universe.getValues()) {
            if (membershipFunction.getMembership(value) > threshold) {
                cut.add(value);
            }
        }
        return cut;
    }

    public double getHeight() {
        double max = 0.0;
        for (double value : universe.getValues()) {
            double membership = membershipFunction.getMembership(value);
            if (membership > max) {
                max = membership;
            }
        }
        return max;
    }

    public List<Double> getComplement() {
        List<Double> complement = new ArrayList<>();
        for (double value : universe.getValues()) {
            if (membershipFunction.getMembership(value) == 0.0) {
                complement.add(value);
            }
        }
        return complement;
    }

    public boolean isEmpty() {
        return getHeight() == 0.0;
    }

    public boolean isConvex() {
        return false;
    }

    public boolean isNormal() {
        return getHeight() == 1.0;
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
