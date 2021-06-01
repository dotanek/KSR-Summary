package subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Subject {
    private Map<String,Double> attributes;
    private String bonusAttribute;

    public Subject(Map<String, Double> attributes, String bonusAttribute) {
        this.attributes = attributes;
        this.bonusAttribute = bonusAttribute;
    }

    public String getBonusAttribute() {
        return bonusAttribute;
    }

    public Set<String> getAttribuesNames() {
        return attributes.keySet();
    }

    public double getAttribute(String name) {
        return attributes.get(name);
    }


    public static List<Subject> getSubjects(List<Subject> subjects,String name) {
        List<Subject> filteredSubjects = new ArrayList<>();
        for (Subject subject : subjects) {
            if (subject.getBonusAttribute().equals(name)) {
                filteredSubjects.add(subject);
            }
        }
        return filteredSubjects;
    }

}
