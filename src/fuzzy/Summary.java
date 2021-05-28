package fuzzy;

import fuzzy.quantifier.Quantifier;
import subject.Attribute;

public class Summary {
    private Quantifier quantifier;
    private Label qualifier;
    private Label summarizer;
    private Attribute attribute;
    private String subjectName;

    public Summary(Quantifier quantifier, Label qualifier, Label summarizer, Attribute attribute, String subjectName) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.attribute = attribute;
        this.subjectName = subjectName;
    }

    public double getTruthDegree() {
        return 0.0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(quantifier.getName());
        stringBuilder.append(" "+subjectName);
        stringBuilder.append(" JEST/MA");
        stringBuilder.append(" "+summarizer.getName());

        return stringBuilder.toString();
    }
}
