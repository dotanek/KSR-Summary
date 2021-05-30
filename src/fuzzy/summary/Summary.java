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

        double cardinality = (quantifier instanceof AbsoluteQuantifier) ? sCount : sCount / (double)subjects.size();

        return quantifier.getMembership(cardinality);
    }

    private double getSecondFormTruthDegree() {
        if (quantifier instanceof AbsoluteQuantifier) {
            System.out.println("Absolute quantifier cannot be used in the second form summary.");
            return 0.0;
        }

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

    public double getImprecisionDegree() {
        double fuzziness = 1.0;
        for (Label summarizer : summarizers) { // Should I count qualifiers as well?

            fuzziness *= summarizer.getFuzziness();
        }
        return 1 - Math.pow(fuzziness,1 / (double)summarizers.size());
    }

    /*public double getCoverageDegree() {
        if (qualifiers == null) {
            return getFirstFormCoverageDegree();
        }
        return getSecondFormCoverageDegree();
    }*/

    /*public double getSecondFormCoverageDegree() {

        double coverageIntersection = 0.0;
        double coverageQualifier = 0.0;

        List<Label> all = new ArrayList<>();
        all.addAll(summarizers);
        all.addAll(qualifiers);

        for (Subject subject : subjects) {
            if (LinguisticVariable.getIntersectionMembership(subject,all) > 0.0) {
                coverageIntersection += 1.0;
            }

            if (LinguisticVariable.getIntersectionMembership(subject,qualifiers) > 0.0) {
                coverageQualifier += 1.0;
            }
        }

        return coverageIntersection / coverageQualifier;
    }*/

    public double getSecondFormCoverageDegree(Label qualifier, Label summarizer) {
        double coverageIntersection = 0.0;
        double coverageQualifier = 0.0;

        for (Subject subject : subjects) {
            if (LinguisticVariable.getIntersectionMembership(subject,summarizer,qualifier) > 0.0) {
                coverageIntersection += 1.0;
            }

            if (qualifier.getMembership(subject) > 0.0) {
                coverageQualifier += 1.0;
            }
        }

        return coverageIntersection / coverageQualifier;
    }

    /*public double getFirstFormCoverageDegree() {
        double coverageIntersection = 0.0;

        for (Subject subject : subjects) {
            if (LinguisticVariable.getIntersectionMembership(subject,summarizers) > 0.0) {
                coverageIntersection += 1.0;
            }
        }

        return coverageIntersection / (double)subjects.size();
    }*/

    public double getFirstFormCoverageDegree(Label summarizer) {
        double coverageIntersection = 0.0;

        for (Subject subject : subjects) {
            if (summarizer.getMembership(subject) > 0.0) {
                coverageIntersection += 1.0;
            }
        }

        return coverageIntersection / (double)subjects.size();
    }

    public double getFirstFormAppropriatenessDegree() {
        double appropriatenes = 1.0;

        for (Label summarizer : summarizers) {
            double r = 0.0;
            double T3 = getFirstFormCoverageDegree(summarizer);
            for (Subject subject : subjects) {
                if (summarizer.getMembership(subject) > 0) {
                    r += 1.0;
                }
            }
            appropriatenes *= r / (double)subjects.size() - T3;
        }

        return Math.abs(appropriatenes);
    }

    public double getSecondFormAppropriatenessDegree(Label qualifier) {
        double appropriatenes = 1.0;

        for (Label summarizer : summarizers) {
            double r = 0.0;
            double T3 = getSecondFormCoverageDegree(summarizer,qualifier);
            for (Subject subject : subjects) {
                if (summarizer.getMembership(subject) > 0) {
                    r += 1.0;
                }
            }
            appropriatenes *= r / (double)subjects.size() - T3;
        }

        return Math.abs(appropriatenes);
    }

    public double getSummaryLength() {
        return 2 * Math.pow(0.5,summarizers.size());
    }

    public double getQuantifierImprecisionDegree() {
        if (quantifier instanceof AbsoluteQuantifier) {
            return 1 - quantifier.getSupportCardinality() / (double)subjects.size();
        }
        return 1 - quantifier.getSupportCardinality();
    }

    public double getQuantifierCardinalityDegree() {
        if (quantifier instanceof AbsoluteQuantifier) {
            return 1 - quantifier.getCardinality() / (double)subjects.size();
        }
        return 1 - quantifier.getCardinality();
    }

    public double getSummarizerCardinalityDegree() {
        double cardinality = 1.0;

        for (Label summarizer : summarizers) {
            cardinality *= summarizer.getCardinality();
        }

        return 1 - Math.pow(cardinality,1 / (double)summarizers.size());
    }

    public double getQualifierImprecisionDegree() {
        double imprecision = 1.0;

        for (Label qualifier : qualifiers) {
            imprecision *= qualifier.getFuzziness();
        }

        return 1 - Math.pow(imprecision,1 / (double)qualifiers.size());
    }

    public double getQualifierCardinalityDegree() {
        double cardinality = 1.0;

        for (Label qualifier : qualifiers) {
            cardinality *= qualifier.getCardinality();
        }
        return 1 - Math.pow(cardinality,1 / (double)qualifiers.size());
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
