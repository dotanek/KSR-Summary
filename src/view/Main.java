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

        Subject test = subjects.get(0);

        LinguisticVariable wiek = linguisticVariables.get(0);
        Label bmlody = wiek.getLabels().get(0);
        Label mlody = wiek.getLabels().get(1);
        Label sredni = wiek.getLabels().get(2);
        //double val = 31.0;
        //System.out.println("Membership bmlody = "+bmlody.getMembership(val));
        //System.out.println("Membership mlody = "+mlody.getMembership(val));
        //System.out.println("Membership intersection = "+ Label.getIntersectionMembership(val,bmlody,mlody));
        //
        Summary summary = new Summary(quantifiers.get(8),linguisticVariables.get(0).getLabels().get(2),linguisticVariables.get(1).getLabels().get(0), subjects,"PIŁKARZE");
        Summary summary2 = new Summary(quantifiers.get(8),linguisticVariables.get(0).getLabels().get(2),linguisticVariables.get(1).getLabels().get(1), subjects,"PIŁKARZE");
        Summary summary3 = new Summary(quantifiers.get(8),linguisticVariables.get(0).getLabels().get(2),linguisticVariables.get(1).getLabels().get(2), subjects,"PIŁKARZE");
        Summary summary4 = new Summary(quantifiers.get(8),linguisticVariables.get(0).getLabels().get(2),linguisticVariables.get(1).getLabels().get(3), subjects,"PIŁKARZE");
        Summary summary5 = new Summary(quantifiers.get(8),linguisticVariables.get(0).getLabels().get(2),linguisticVariables.get(1).getLabels().get(4), subjects,"PIŁKARZE");
        System.out.println(summary.toString()+" = "+ summary.getTruthDegree());
        System.out.println(summary2.toString()+" = "+ summary2.getTruthDegree());
        System.out.println(summary3.toString()+" = "+ summary3.getTruthDegree());
        System.out.println(summary4.toString()+" = "+ summary4.getTruthDegree());
        System.out.println(summary5.toString()+" = "+ summary5.getTruthDegree());

        //SummaryGenerator summaryGenerator = new SummaryGenerator(linguisticVariables,quantifiers,subjects);
        //summaryGenerator.generateSingleFirstFormSummaries();
        //summaryGenerator.generateSingleSecondFormSummaries();

    }
}
