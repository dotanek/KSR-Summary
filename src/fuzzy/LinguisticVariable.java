package fuzzy;

import java.util.List;

public class LinguisticVariable {
    private String name;
    private List<Label> labels;

    public LinguisticVariable(String name, List<Label> labels) {
        this.name = name;
        this.labels = labels;
    }

    public Label getLabel(double value) {
        return null;
    }
}
