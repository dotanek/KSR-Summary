package subject;

import java.util.ArrayList;
import java.util.List;

public class Attribute {
    private String name;
    private List<Double> values;

    public Attribute(String name) {
        this.name = name;
        values = new ArrayList<>();
    }

    public Attribute(String name, List<Double> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<Double> getValues() {
        return values;
    }
}
