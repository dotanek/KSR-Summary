package fuzzy.summary;

import fuzzy.Label;
import fuzzy.LinguisticVariable;
import fuzzy.quantifier.AbsoluteQuantifier;
import fuzzy.quantifier.Quantifier;
import subject.Subject;

import java.util.List;

public class Summary {
    private Quantifier quantifier;
    private Label qualifier;
    private Label summarizer;
    private List<Subject> subjects;
    private String subjectName;

    public Summary(Quantifier quantifier, Label qualifier, Label summarizer, List<Subject> subjects, String subjectName) {
        this.quantifier = quantifier;
        this.qualifier = qualifier;
        this.summarizer = summarizer;
        this.subjects = subjects;
        this.subjectName = subjectName;
    }

    public double getTruthDegree() {
        if (qualifier == null) {
            return getFirstFormTruthDegree();
        }
        return getSecondFormTruthDegree();
    }

    private double getFirstFormTruthDegree() {
        double sCount = 0.0;

        for (Subject subject : subjects) {
            sCount += summarizer.getMembership(subject);
        }

        double cardinality = (quantifier instanceof AbsoluteQuantifier) ? sCount : sCount / subjects.size();

        return quantifier.getMembership(cardinality);
    }

    private double getSecondFormTruthDegree() {
        double sCountIntersection = 0.0;
        double sCountQualifier = 0.0;

        for (Subject subject : subjects) {
            sCountIntersection += LinguisticVariable.getIntersectionMembership(subject,summarizer,qualifier);
            sCountQualifier += qualifier.getMembership(subject);
        }

        return quantifier.getMembership(sCountIntersection / sCountQualifier);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(quantifier.getName());
        stringBuilder.append(" "+subjectName);

        if (qualifier != null) {
            stringBuilder.append(", KTÓRZY SĄ/MAJĄ ");
            stringBuilder.append(qualifier.getName());
        }

        stringBuilder.append(" SĄ/MAJĄ ");
        stringBuilder.append(summarizer.getName());

        return stringBuilder.toString();
    }
}
