package fuzzy;

import java.util.List;

public class LinguisticVariable {
    private String name;
    private List<Label> labels;

    public LinguisticVariable(String name, List<Label> labels) {
        this.name = name;
        this.labels = labels;
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
}
