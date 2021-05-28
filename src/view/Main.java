package view;

import database.DataInitialization;
import database.DataLoader;
import database.DatabaseConnection;
import fuzzy.Label;
import fuzzy.LinguisticVariable;
import fuzzy.Summary;
import fuzzy.quantifier.Quantifier;
import javafx.application.Application;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import subject.Attribute;

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
        List<LinguisticVariable> linVariables = DataInitialization.initializeLinguisticVariables(jsonParams);
        List<Quantifier> quantifiers = DataInitialization.initializeAbsoluteQuantifiers(jsonParams);

        DatabaseConnection databaseConnection = new DatabaseConnection();
        databaseConnection.connect();
        List<Attribute> attributes = databaseConnection.getAttributes();

        //Summary summary = new Summary(quantifiers.get(6),null,linVariables.get(3).getLabels().get(0),attributes.get(0),"PIŁKARZY");
        //System.out.println(summary.toString());
    }
}
