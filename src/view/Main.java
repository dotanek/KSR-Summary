package view;

import database.DataInitialization;
import database.DataLoader;
import database.DatabaseConnection;
import fuzzy.Label;
import fuzzy.LinguisticVariable;
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

        List<Label> qualifiers = new ArrayList<>();
        List<Label> summarizers = new ArrayList<>();

        Quantifier quantifier = summaryGenerator.getQuantifier("OKOŁO 5 TYSIĘCY");

        //qualifiers.add(summaryGenerator.getLabel("W ŚREDNIM WIEKU"));

        summarizers.add(summaryGenerator.getLabel("W ŚREDNIM WIEKU"));


        Summary summary = new Summary(quantifier,null,summarizers,subjects,"PIŁKARZE");
        System.out.println(summary.getSummaryText());
        System.out.println(summary.getQualitiesText());
    }
}
