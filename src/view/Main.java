package view;

import database.DataInitialization;
import database.DataLoader;
import database.DatabaseConnection;
import fuzzy.Label;
import fuzzy.LinguisticVariable;
import fuzzy.quantifier.RelativeQuantifier;
import fuzzy.summary.MultiSubjectSummary;
import fuzzy.summary.Summary;
import fuzzy.quantifier.Quantifier;
import fuzzy.summary.SummaryGenerator;
import javafx.application.Application;
import javafx.stage.Stage;
import org.json.JSONObject;
import subject.Subject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    static List<LinguisticVariable> linguisticVariables;
    static List<Quantifier> absoluteQuantifiers;
    static List<Quantifier> relativeQuantifiers;
    static List<Subject> subjects;
    static SummaryGenerator summaryGenerator;
    static List<Label> summarizers;
    static List<Label> qualifiers;
    static List<Subject> subjects1;
    static List<Subject> subjects2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1300, 600));
        primaryStage.show();
    }

    public static ArrayList<String> getLinguisticVariableNames() {
        ArrayList<String> result = new ArrayList<>();
        for(LinguisticVariable variable : linguisticVariables) {
            result.add(variable.getName());
        }
        return result;
    }

    public static ArrayList<String> getLabelNames() {
        ArrayList<String> result = new ArrayList<>();
        for(LinguisticVariable variable : linguisticVariables) {
            List<Label> labels = variable.getLabels();
            for(Label label : labels) {
                result.add(label.getName());
            }
        }
        return result;
    }

    public static ArrayList<String> getLabelNames(String linguisticVariableName) {
        ArrayList<String> result = new ArrayList<>();
        for(LinguisticVariable variable : linguisticVariables) {
            if(!variable.getName().equals(linguisticVariableName)) {
                continue;
            }
            List<Label> labels = variable.getLabels();
            for(Label label : labels) {
                result.add(label.getName());
            }
        }
        return result;
    }

    public static ArrayList<String> getQuantifierNames(String type) {
        if(!type.equals("absolute") && !type.equals("relative")) {
            return null;
        }
        ArrayList<String> result = new ArrayList<>();
        List<Quantifier> quantifiers;
        quantifiers = type.equals("absolute") ? absoluteQuantifiers : relativeQuantifiers;
        for(Quantifier quantifier : quantifiers) {
            result.add(quantifier.getName());
        }
        return result;
    }

    public static Result generateSummary(String quantifierName, ArrayList<String> summarizerNames,
                                         ArrayList<String> qualifierNames, ArrayList<Double> weights,
                                         String firstChosenSubject, String secondChosenSubject, int chosenForm) {
        summarizers = new ArrayList<>();
        for (String name : summarizerNames) {
            summarizers.add(summaryGenerator.getLabel(name));
        }
        qualifiers = new ArrayList<>();
        for (String name : qualifierNames) {
            qualifiers.add(summaryGenerator.getLabel(name));
        }
        Result result;

        // Wielopodmiotowe
        if(!firstChosenSubject.isEmpty() && !secondChosenSubject.isEmpty() && chosenForm > 0) {
            subjects1 = Subject.getSubjects(subjects,firstChosenSubject);
            subjects2 = Subject.getSubjects(subjects,secondChosenSubject);
            RelativeQuantifier relativeQuantifier = (RelativeQuantifier) summaryGenerator.getQuantifier(quantifierName);

            MultiSubjectSummary multiSummary = new MultiSubjectSummary
                    (relativeQuantifier, qualifiers, summarizers, subjects1, subjects2,"PI??KARZE");
            String resultText = "";
            double resultTruthDegree = 0;
            if(chosenForm == 1) {
                resultText = multiSummary.getFirstFormSummaryText();
                resultTruthDegree = multiSummary.getFirstFormTruthDegree();
            }
            if(chosenForm == 2) {
                resultText = multiSummary.getSecondFormSummaryText();
                resultTruthDegree = multiSummary.getSecondFormTruthDegree();
            }
            if(chosenForm == 3) {
                resultText = multiSummary.getThirdFormSummaryText();
                resultTruthDegree = multiSummary.getThirdFormTruthDegree();
            }
            if(chosenForm == 4) {
                resultText = multiSummary.getFourthFormSummaryText();
                resultTruthDegree = multiSummary.getFourthFormTruthDegree();
            }
            result = new Result(resultText, resultTruthDegree);
        }
        // Zwyk??e
        else {
            Quantifier quantifier = summaryGenerator.getQuantifier(quantifierName);
            Summary summary = new Summary(quantifier, qualifiers, summarizers, subjects, "PI??KARZE", weights);
            ArrayList<Double> qualitiesArray = summary.getQualitiesArray();
            result = new Result(
                    summary.getSummaryText(), qualitiesArray.get(0), qualitiesArray.get(1), qualitiesArray.get(2),
                    qualitiesArray.get(3), qualitiesArray.get(4), qualitiesArray.get(5), qualitiesArray.get(6),
                    qualitiesArray.get(7), qualitiesArray.get(8), qualitiesArray.get(9), qualitiesArray.get(10),
                    qualitiesArray.get(11)
            );
        }
        return result;
    }

    public static void main(String[] args){
        String dataPath = System.getProperty("user.dir") + "\\data";
        DataLoader dataLoader = new DataLoader(dataPath);
        JSONObject jsonParams = dataLoader.loadMembershipParams();

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        linguisticVariables = DataInitialization.initializeLinguisticVariables(jsonParams);
        absoluteQuantifiers = DataInitialization.initializeAbsoluteQuantifiers(jsonParams);
        relativeQuantifiers = DataInitialization.initializeRelativeQuantifiers(jsonParams);
        subjects = databaseConnection.getSubjects();

        summaryGenerator = new SummaryGenerator(
                linguisticVariables,
                absoluteQuantifiers,
                relativeQuantifiers,
                subjects
        );

        subjects1 = Subject.getSubjects(subjects,"KOREA REPUBLIC");
        subjects2 = Subject.getSubjects(subjects,"ARGENTINA");


        qualifiers = new ArrayList<>();
        summarizers = new ArrayList<>();

        //Quantifier quantifier = summaryGenerator.getQuantifier("OKO??O 5 TYSI??CY");
        //summarizers.add(summaryGenerator.getLabel("W ??REDNIM WIEKU"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("POWY??EJ 15 TYSI??CY");
        //summarizers.add(summaryGenerator.getLabel("BARDZO MA??O ZARABIAJ??CY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE NIKT");
        //summarizers.add(summaryGenerator.getLabel("WSPANIA??Y"));

//        Quantifier quantifier = summaryGenerator.getQuantifier("WI??KSZO????");
//        summarizers.add(summaryGenerator.getLabel("PRZECI??TNIE WYSOKI"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("MNIEJSZO????");
        //summarizers.add(summaryGenerator.getLabel("PRZECI??TNIE WYSOKI"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("MNIEJSZO????");
        //summarizers.add(summaryGenerator.getLabel("PRZECI??TNIE SILNY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE WSZYSCY");
        //qualifiers.add(summaryGenerator.getLabel("WSPANIA??Y"));
        //summarizers.add(summaryGenerator.getLabel("BARDZO DU??O ZARABIAJ??CY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("OKO??O PO??OWY");
        //qualifiers.add(summaryGenerator.getLabel("BARDZO SILNY"));
        //summarizers.add(summaryGenerator.getLabel("UTALENTOWANY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE WSZYSCY");
        //qualifiers.add(summaryGenerator.getLabel("BARDZO DU??O ZARABIAJ??CY"));
        //summarizers.add(summaryGenerator.getLabel("WSPANIA??Y"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("WI??KSZO????");
        //qualifiers.add(summaryGenerator.getLabel("LEKKI"));
        //summarizers.add(summaryGenerator.getLabel("ZWINNY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE WSZYSCY");
        //qualifiers.add(summaryGenerator.getLabel("MIZERNY"));
        //summarizers.add(summaryGenerator.getLabel("BARDZO MA??O ZARABIAJ??CY"));
        //summarizers.add(summaryGenerator.getLabel("BARDZO TANI"));

        //RelativeQuantifier quantifier = (RelativeQuantifier) summaryGenerator.getQuantifier("PRAWIE NIKT");
        //summarizers.add(summaryGenerator.getLabel("WYSOKI"));
        //qualifiers.add(summaryGenerator.getLabel("MA??O ZARABIAJ??CY"));

        //MultiSubjectSummary multiSummary = new MultiSubjectSummary(quantifier,null, summarizers, subjects1, subjects2,"PI??KARZE");
        //System.out.println("Fourth: "+multiSummary.getFourthFormSummaryText());
        //System.out.println("T = "+multiSummary.getFourthFormTruthDegree());
        //System.out.println("Second: "+multiSummary.getSecondFormSummaryText());
        //System.out.println("Third: "+multiSummary.getThirdFormSummaryText());
        //System.out.println(multiSummary.getSecondFormTruthDegree());

//        Summary summary = new Summary(quantifier,null,summarizers,subjects,"PI??KARZE");
//        System.out.println(summary.getSummaryText());
//        System.out.println(summary.getQualitiesText());
        launch(args);
    }
}
