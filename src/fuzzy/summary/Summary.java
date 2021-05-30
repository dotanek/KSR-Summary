package fuzzy.summary;

import fuzzy.Label;
import fuzzy.LinguisticVariable;
import fuzzy.quantifier.AbsoluteQuantifier;
import fuzzy.quantifier.Quantifier;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class Summary {
    private Quantifier quantifier;
    private List<Label> qualifiers;
    private List<Label> summarizers;
    private List<Subject> subjects;
    private String subjectName;

    public Summary(Quantifier quantifier, List<Label> qualifiers, List<Label> summarizers, List<Subject> subjects, String subjectName) {
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.subjects = subjects;
        this.subjectName = subjectName;
    }

    public double getTruthDegree() {
        if (qualifiers == null || qualifiers.size() == 0) {
            return getFirstFormTruthDegree();
        }
        return getSecondFormTruthDegree();
    }

    private double getFirstFormTruthDegree() {
        double sCount = 0.0;

        for (Subject subject : subjects) {
            sCount += LinguisticVariable.getIntersectionMembership(subject, summarizers);
        }

        double cardinality = (quantifier instanceof AbsoluteQuantifier) ? sCount : sCount / subjects.size();

        return quantifier.getMembership(cardinality);
    }

    private double getSecondFormTruthDegree() {
        double sCountNominator = 0.0;
        double sCountDenominator = 0.0;

        for (Subject subject : subjects) {
            List<Label> all = new ArrayList<>();
            all.addAll(summarizers);
            all.addAll(qualifiers);
            sCountNominator += LinguisticVariable.getIntersectionMembership(subject,all);
            sCountDenominator += LinguisticVariable.getIntersectionMembership(subject,qualifiers);
        }

        return quantifier.getMembership(sCountNominator / sCountDenominator);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(quantifier.getName());
        stringBuilder.append(" "+subjectName);

        if (qualifiers != null && qualifiers.size() != 0) {
            stringBuilder.append(", KTÓRZY SĄ/MAJĄ ");
            stringBuilder.append(qualifiers.get(0).getName());

            for (int i = 1; i < qualifiers.size(); i++) {
                stringBuilder.append(" ORAZ ");
                stringBuilder.append(qualifiers.get(i).getName());
            }
        }

        stringBuilder.append(" SĄ/MAJĄ ");
        stringBuilder.append(summarizers.get(0).getName());

        for (int i = 1; i < summarizers.size(); i++) {
            stringBuilder.append(" ORAZ ");
            stringBuilder.append(summarizers.get(i).getName());
        }

        return stringBuilder.toString();
    }
}
