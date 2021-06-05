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

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1000, 600));
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
                                         String firstChosenSubject, String secondChosenSubject) {
        if(!firstChosenSubject.isEmpty() && !secondChosenSubject.isEmpty()) {
            // Wielopodmiotowe
        }
        Quantifier quantifier = summaryGenerator.getQuantifier(quantifierName);
        summarizers = new ArrayList<>();
        for(String name : summarizerNames) {
            summarizers.add(summaryGenerator.getLabel(name));
        }
        qualifiers = new ArrayList<>();
        for(String name : qualifierNames) {
            qualifiers.add(summaryGenerator.getLabel(name));
        }
        if(qualifiers.size() == 0) {
            qualifiers = null;
        }

        // Dodać ustawianie wag
        Summary summary = new Summary(quantifier,qualifiers,summarizers,subjects,"PIŁKARZE");
        ArrayList<Double> qualitiesArray = summary.getQualitiesArray();
        Result result = new Result(
                summary.getSummaryText(), qualitiesArray.get(0), qualitiesArray.get(1), qualitiesArray.get(2),
                qualitiesArray.get(3), qualitiesArray.get(4), qualitiesArray.get(5), qualitiesArray.get(6),
                qualitiesArray.get(7), qualitiesArray.get(8), qualitiesArray.get(9), qualitiesArray.get(10),
                qualitiesArray.get(11)
        );
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

        List<Subject> subjects2 = Subject.getSubjects(subjects,"ARGENTINA");
        List<Subject> subjects1 = Subject.getSubjects(subjects,"KOREA REPUBLIC");



        qualifiers = new ArrayList<>();
        summarizers = new ArrayList<>();

        //Quantifier quantifier = summaryGenerator.getQuantifier("OKOŁO 5 TYSIĘCY");
        //summarizers.add(summaryGenerator.getLabel("W ŚREDNIM WIEKU"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("POWYŻEJ 15 TYSIĘCY");
        //summarizers.add(summaryGenerator.getLabel("BARDZO MAŁO ZARABIAJĄCY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE NIKT");
        //summarizers.add(summaryGenerator.getLabel("WSPANIAŁY"));

//        Quantifier quantifier = summaryGenerator.getQuantifier("WIĘKSZOŚĆ");
//        summarizers.add(summaryGenerator.getLabel("PRZECIĘTNIE WYSOKI"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("MNIEJSZOŚĆ");
        //summarizers.add(summaryGenerator.getLabel("PRZECIĘTNIE WYSOKI"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("MNIEJSZOŚĆ");
        //summarizers.add(summaryGenerator.getLabel("PRZECIĘTNIE SILNY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE WSZYSCY");
        //qualifiers.add(summaryGenerator.getLabel("WSPANIAŁY"));
        //summarizers.add(summaryGenerator.getLabel("BARDZO DUŻO ZARABIAJĄCY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("OKOŁO POŁOWY");
        //qualifiers.add(summaryGenerator.getLabel("BARDZO SILNY"));
        //summarizers.add(summaryGenerator.getLabel("UTALENTOWANY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE WSZYSCY");
        //qualifiers.add(summaryGenerator.getLabel("BARDZO DUŻO ZARABIAJĄCY"));
        //summarizers.add(summaryGenerator.getLabel("WSPANIAŁY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("WIĘKSZOŚĆ");
        //qualifiers.add(summaryGenerator.getLabel("LEKKI"));
        //summarizers.add(summaryGenerator.getLabel("ZWINNY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE WSZYSCY");
        //qualifiers.add(summaryGenerator.getLabel("MIZERNY"));
        //summarizers.add(summaryGenerator.getLabel("BARDZO MAŁO ZARABIAJĄCY"));
        //summarizers.add(summaryGenerator.getLabel("BARDZO TANI"));

        //RelativeQuantifier quantifier = (RelativeQuantifier) summaryGenerator.getQuantifier("PRAWIE NIKT");
        //summarizers.add(summaryGenerator.getLabel("WYSOKI"));
        //qualifiers.add(summaryGenerator.getLabel("MAŁO ZARABIAJĄCY"));

        //MultiSubjectSummary multiSummary = new MultiSubjectSummary(quantifier,null, summarizers, subjects1, subjects2,"PIŁKARZE");
        //System.out.println("Fourth: "+multiSummary.getFourthFormSummaryText());
        //System.out.println("T = "+multiSummary.getFourthFormTruthDegree());
        //System.out.println("Second: "+multiSummary.getSecondFormSummaryText());
        //System.out.println("Third: "+multiSummary.getThirdFormSummaryText());
        //System.out.println(multiSummary.getSecondFormTruthDegree());

//        Summary summary = new Summary(quantifier,null,summarizers,subjects,"PIŁKARZE");
//        System.out.println(summary.getSummaryText());
//        System.out.println(summary.getQualitiesText());
        launch(args);
    }
}
