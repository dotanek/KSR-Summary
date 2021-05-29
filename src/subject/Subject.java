package subject;

import java.util.Map;
import java.util.Set;

public class Subject {
    private Map<String,Double> attributes;

    public Subject(Map<String, Double> attributes) {
        this.attributes = attributes;
    }

    public Set<String> getAttribuesNames() {
        return attributes.keySet();
    }

    public double getAttribute(String name) {
        return attributes.get(name);
    }
}
