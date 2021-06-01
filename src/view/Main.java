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

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        */
    }

    public static void main(String[] args){
        //launch(args);
        String dataPath = System.getProperty("user.dir") + "\\data";
        DataLoader dataLoader = new DataLoader(dataPath);
        JSONObject jsonParams = dataLoader.loadMembershipParams();

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();

        List<LinguisticVariable> linguisticVariables = DataInitialization.initializeLinguisticVariables(jsonParams);
        List<Quantifier> absoluteQuantifiers = DataInitialization.initializeAbsoluteQuantifiers(jsonParams);
        List<Quantifier> relativeQuantifiers = DataInitialization.initializeRelativeQuantifiers(jsonParams);
        List<Subject> subjects = databaseConnection.getSubjects();

        SummaryGenerator summaryGenerator = new SummaryGenerator(
                linguisticVariables,
                absoluteQuantifiers,
                relativeQuantifiers,
                subjects
        );

        List<Subject> subjects1 = Subject.getSubjects(subjects,"ARGENTINA");
        List<Subject> subjects2 = Subject.getSubjects(subjects,"PORTUGAL");

        List<Label> qualifiers = new ArrayList<>();
        List<Label> summarizers = new ArrayList<>();

        //Quantifier quantifier = summaryGenerator.getQuantifier("OKOŁO 5 TYSIĘCY");
        //summarizers.add(summaryGenerator.getLabel("W ŚREDNIM WIEKU"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("POWYŻEJ 15 TYSIĘCY");
        //summarizers.add(summaryGenerator.getLabel("BARDZO MAŁO ZARABIAJĄCY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("PRAWIE NIKT");
        //summarizers.add(summaryGenerator.getLabel("WSPANIAŁY"));

        //Quantifier quantifier = summaryGenerator.getQuantifier("WIĘKSZOŚĆ");
        //summarizers.add(summaryGenerator.getLabel("PRZECIĘTNIE WYSOKI"));

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

        RelativeQuantifier quantifier = (RelativeQuantifier) summaryGenerator.getQuantifier("PRAWIE NIKT");
        summarizers.add(summaryGenerator.getLabel("PRZECIĘTNIE WYSOKI"));
        qualifiers.add(summaryGenerator.getLabel("MAŁO ZARABIAJĄCY"));

        MultiSubjectSummary multiSummary = new MultiSubjectSummary(quantifier,qualifiers, summarizers, subjects1, subjects2,"PIŁKARZE");
        System.out.println("First: "+multiSummary.getThirdFormSummaryText());
        System.out.println("T = "+multiSummary.getThirdFormTruthDegree());
        //System.out.println("Second: "+multiSummary.getSecondFormSummaryText());
        //System.out.println("Third: "+multiSummary.getThirdFormSummaryText());
        //System.out.println(multiSummary.getSecondFormTruthDegree());

        //Summary summary = new Summary(quantifier,null,summarizers,subjects,"PIŁKARZE");
        //System.out.println(summary.getSummaryText());
        //System.out.println(summary.getQualitiesText());
    }
}
