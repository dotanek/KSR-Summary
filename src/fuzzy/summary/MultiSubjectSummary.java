package fuzzy.summary;

import fuzzy.Label;
import fuzzy.LinguisticVariable;
import fuzzy.quantifier.AbsoluteQuantifier;
import fuzzy.quantifier.Quantifier;
import fuzzy.quantifier.RelativeQuantifier;
import subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class MultiSubjectSummary {
    private RelativeQuantifier quantifier;
    private List<Label> qualifiers;
    private List<Label> summarizers;
    private List<Subject> subjects1;
    private List<Subject> subjects2;
    private String subjectName;

    public MultiSubjectSummary(RelativeQuantifier quantifier, List<Label> qualifiers, List<Label> summarizers, List<Subject> subjects1, List<Subject> subjects2, String subjectName) {
        this.quantifier = quantifier;
        this.qualifiers = qualifiers;
        this.summarizers = summarizers;
        this.subjects1 = subjects1;
        this.subjects2 = subjects2;
        this.subjectName = subjectName;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public double getFirstFormTruthDegree() {
        double sCountSubjects1 = 0.0;
        double sCountSubjects2 = 0.0;
        for (Subject subject : subjects1) {
            sCountSubjects1 += LinguisticVariable.getIntersectionMembership(subject,summarizers);
        }

        sCountSubjects1 /= (double) subjects1.size();

        for (Subject subject : subjects2) {
            sCountSubjects2 += LinguisticVariable.getIntersectionMembership(subject,summarizers);
        }

        sCountSubjects2 /= (double) subjects2.size();

        double result = quantifier.getMembership(sCountSubjects1 / (sCountSubjects1 + sCountSubjects2));
        return round(result);
    }

    public double getSecondFormTruthDegree() {
        double sCountSubjects1 = 0.0;
        double sCountSubjects2 = 0.0;
        for (Subject subject : subjects1) {
            sCountSubjects1 += LinguisticVariable.getIntersectionMembership(subject,summarizers);
        }

        sCountSubjects1 /= (double) subjects1.size();

        List<Label> all = new ArrayList<>();
        all.addAll(summarizers);
        if(qualifiers != null) {
            all.addAll(qualifiers);
        }

        for (Subject subject : subjects2) {
            sCountSubjects2 += LinguisticVariable.getIntersectionMembership(subject,all);
        }

        sCountSubjects2 /= (double) subjects2.size();

        double result = quantifier.getMembership(sCountSubjects1 / (sCountSubjects1 + sCountSubjects2));
        return round(result);
    }

    public double getThirdFormTruthDegree() {
        double sCountSubjects1 = 0.0;
        double sCountSubjects2 = 0.0;

        List<Label> all = new ArrayList<>();
        all.addAll(summarizers);
        all.addAll(qualifiers);

        for (Subject subject : subjects1) {
            sCountSubjects1 += LinguisticVariable.getIntersectionMembership(subject,all);
        }

        sCountSubjects1 /= (double) subjects1.size();

        for (Subject subject : subjects2) {
            sCountSubjects2 += LinguisticVariable.getIntersectionMembership(subject,summarizers);
        }

        sCountSubjects2 /= (double) subjects2.size();

        double result = quantifier.getMembership(sCountSubjects1 / (sCountSubjects1 + sCountSubjects2));
        return round(result);
    }

    public double getFourthFormTruthDegree() {
        double membershipNominator = 0.0;
        double membershipDenominator = 0.0;

        for (Subject subject : subjects1) {
            double membership = LinguisticVariable.getIntersectionMembership(subject,summarizers);
            membershipNominator += membership;
            membershipDenominator += membership;
        }

        for (Subject subject : subjects2) {
            membershipDenominator += LinguisticVariable.getIntersectionMembership(subject,summarizers);
        }

        double result = membershipNominator / membershipDenominator;
        return round(result);
    }

    public String getFirstFormSummaryText() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(quantifier.getName());
        stringBuilder.append(" "+subjectName+" Z "+subjects1.get(0).getBonusAttribute());


        stringBuilder.append(" W PORÓWNANIU Z");
        stringBuilder.append(" "+subjectName+" Z "+subjects2.get(0).getBonusAttribute());

        stringBuilder.append(" SĄ/MAJĄ ");
        stringBuilder.append(summarizers.get(0).getName());

        for (int i = 1; i < summarizers.size(); i++) {
            stringBuilder.append(" ORAZ ");
            stringBuilder.append(summarizers.get(i).getName());
        }

        return stringBuilder.toString();
    }

    public String getSecondFormSummaryText() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(quantifier.getName());
        stringBuilder.append(" "+subjectName+" Z "+subjects1.get(0).getBonusAttribute());

        stringBuilder.append(" W PORÓWNANIU Z");
        stringBuilder.append(" "+subjectName+" Z "+subjects2.get(0).getBonusAttribute());

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

    public String getThirdFormSummaryText() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(quantifier.getName());
        stringBuilder.append(" "+subjectName+" Z "+subjects1.get(0).getBonusAttribute());

        if (qualifiers != null && qualifiers.size() != 0) {
            stringBuilder.append(", KTÓRZY SĄ/MAJĄ ");
            stringBuilder.append(qualifiers.get(0).getName());

            for (int i = 1; i < qualifiers.size(); i++) {
                stringBuilder.append(" ORAZ ");
                stringBuilder.append(qualifiers.get(i).getName());
            }
        }

        stringBuilder.append(" W PORÓWNANIU Z");
        stringBuilder.append(" "+subjectName+" Z "+subjects2.get(0).getBonusAttribute());

        stringBuilder.append(" SĄ/MAJĄ ");
        stringBuilder.append(summarizers.get(0).getName());

        for (int i = 1; i < summarizers.size(); i++) {
            stringBuilder.append(" ORAZ ");
            stringBuilder.append(summarizers.get(i).getName());
        }

        return stringBuilder.toString();
    }

    public String getFourthFormSummaryText() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WIĘCEJ " + subjectName + " Z " + subjects1.get(0).getBonusAttribute());
        stringBuilder.append(" NIŻ " + subjectName + " Z " + subjects2.get(0).getBonusAttribute());

        stringBuilder.append(" SĄ/MAJĄ ");
        stringBuilder.append(summarizers.get(0).getName());

        for (int i = 1; i < summarizers.size(); i++) {
            stringBuilder.append(" ORAZ ");
            stringBuilder.append(summarizers.get(i).getName());
        }

        return stringBuilder.toString();
    }

}
