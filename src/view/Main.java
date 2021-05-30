package view;

import database.DataInitialization;
import database.DataLoader;
import database.DatabaseConnection;
import fuzzy.Label;
import fuzzy.LinguisticVariable;
import fuzzy.summary.Summary;
import fuzzy.quantifier.Quantifier;
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
        List<Quantifier> quantifiers = DataInitialization.initializeAllQuantifiers(jsonParams);
        //Subjects subjects = databaseConnection.getSubjects();
        List<Subject> subjects = databaseConnection.getSubjects();

        List<Label> summarizers = new ArrayList<>();
        List<Label> qualifiers = new ArrayList<>();
        summarizers.add(linguisticVariables.get(3).getLabels().get(0));
        summarizers.add(linguisticVariables.get(4).getLabels().get(0));
        summarizers.add(linguisticVariables.get(6).getLabels().get(1));
        summarizers.add(linguisticVariables.get(5).getLabels().get(2));
        qualifiers.add(linguisticVariables.get(0).getLabels().get(1));

        for (Quantifier quantifier : quantifiers) {
            Summary summary = new Summary(quantifier,qualifiers,summarizers,subjects,"PIŁKARZE");
            System.out.println(summary.toString() + " (" + summary.getTruthDegree() + ")");
        }
    }
}
