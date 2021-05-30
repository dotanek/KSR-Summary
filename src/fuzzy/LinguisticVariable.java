package fuzzy;

import subject.Subject;

import java.util.List;

public class LinguisticVariable {
    private String name;
    private List<Label> labels;

    public LinguisticVariable(String name, List<Label> labels) {
        this.name = name;
        this.labels = labels;

        for (Label label : labels) {
            label.setLinguisticVariable(this);
        }
    }

    public String getName() {
        return name;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public Label getLabel(double value) {
        Label topLabel= null;
        double topMembership = Double.NEGATIVE_INFINITY;
        for (Label label : labels) {
            double currentMembership = label.getMembership(value);
            if (topMembership < currentMembership) {
                topLabel = label;
                topMembership = currentMembership;
            }
        }
        return topLabel;
    }

    public static double getIntersectionMembership(Subject subject, Label... sets) {
        double minMembership = 1.0;

        for (Label set : sets) {
            double currentMembership = set.getMembership(subject);
            if (currentMembership < minMembership) {
                minMembership = currentMembership;
            }
        }

        return minMembership;
    }

    public static double getIntersectionMembership(Subject subject, List<Label> sets) {
        double minMembership = 1.0;

        for (Label set : sets) {
            double currentMembership = set.getMembership(subject);
            if (currentMembership < minMembership) {
                minMembership = currentMembership;
            }
        }

        return minMembership;
    }
}
