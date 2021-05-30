package fuzzy.summary;

import fuzzy.Label;
import fuzzy.LinguisticVariable;
import fuzzy.quantifier.AbsoluteQuantifier;
import fuzzy.quantifier.Quantifier;
import fuzzy.quantifier.RelativeQuantifier;
import subject.Subject;
import utilities.Pair;

import java.util.ArrayList;
import java.util.List;

public class SummaryGenerator {
    private final int QUALIFIER_MIN = 1;
    private final int QUALIFIER_MAX = 3;

    private List<LinguisticVariable> variables;
    private List<Quantifier> absoluteQuantifiers;
    private List<Quantifier> relativeQuantifiers;
    private List<Subject> subjects;

    public SummaryGenerator(List<LinguisticVariable> variables, List<Quantifier> absoluteQuantifiers, List<Quantifier> relativeQuantifiers, List<Subject> subjects) {
        this.variables = variables;
        this.absoluteQuantifiers = absoluteQuantifiers;
        this.relativeQuantifiers = relativeQuantifiers;
        this.subjects = subjects;
    }

    public Label getLabel(String name) {
        for (LinguisticVariable variable : variables) {
            for (Label label : variable.getLabels()) {
                if (label.getName().equals(name)) {
                    return label;
                }
            }
        }
        return null;
    }

    public Quantifier getQuantifier(String name) {
        List<Quantifier> quantifiers = new ArrayList<>();
        quantifiers.addAll(absoluteQuantifiers);
        quantifiers.addAll(relativeQuantifiers);

        for (Quantifier quantifier : quantifiers) {
            if (quantifier.getName().equals(name)) {
                return quantifier;
            }
        }
        return null;
    }

    private List<List<Label>> generateQualifierSets() {
        List<List<Label>> qualifierSets = new ArrayList<>();

        for (LinguisticVariable variable : variables) {
            List<Label> labels = variable.getLabels();
            for (int i = QUALIFIER_MIN-1; i < QUALIFIER_MAX; i++) {
                List<Label> qualifiers = new ArrayList<>();
                for (int j = 0; j  <= i; j++) {
                    qualifiers.add(labels.get(j));
                }
                qualifierSets.add(qualifiers);
            }
        }
        return qualifierSets;
    }

    public List<Summary> generateOptimalSummaries() {
        List<List<Label>> qualifierSets = generateQualifierSets();

        //for ()

        return null;
    }
}
