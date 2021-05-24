package view;

import database.DataInitialization;
import database.DataLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.json.JSONObject;

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
        DataInitialization.initializeLinguisticVariables(jsonParams);
        DataInitialization.initializeAbsoluteQuantifiers(jsonParams);
        //DatabaseConnection databaseConnection = new DatabaseConnection();
        //databaseConnection.connect();
    }
}
