package fuzzy;

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
        return 0.0;
    }

    public List<Double> getSupport() {
        return null;
    }

    public List<Double> getAlphaCut(double threshold) {
        return null;
    }

    public double getHeight() {
        return 0.0;
    }

    public List<Double> getCompliment() {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean isConvex() {
        return false;
    }

    public boolean isNormal() {
        return false;
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
