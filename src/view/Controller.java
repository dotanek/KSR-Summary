package view;

import fuzzy.quantifier.Quantifier;
import fuzzy.summary.SummaryGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private ArrayList<String> linguisticVariables = Main.getLinguisticVariableNames();
    private ArrayList<String> summarizerLabelNames = new ArrayList<>();
    private ArrayList<String> quantifierNames = Main.getQuantifierNames("absolute");
    private String chosenSummarizerName;
    private String chosenQuantifierName;
    private ObservableList<Result> results = FXCollections.observableArrayList();
    //private ArrayList<Double> results = new ArrayList<>();

    @FXML
    private RadioButton absoluteRadioButton;
    @FXML
    private RadioButton relativeRadioButton;
    @FXML
    private ChoiceBox<String> quantifierChoicebox;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private ChoiceBox<String> linguisticVariableChoicebox;
    @FXML
    private ChoiceBox<String> summarizerChoicebox;

    @FXML
    private Button generateButton;

    @FXML
    private TableView<Result> tableView;
    @FXML
    private TableColumn<Result, String> summaryCol;
    @FXML
    private TableColumn<Result, Double> tCol;
    @FXML
    private TableColumn<Result, Double> t1Col;
    @FXML
    private TableColumn<Result, Double> t2Col;
    @FXML
    private TableColumn<Result, Double> t3Col;
    @FXML
    private TableColumn<Result, Double> t4Col;
    @FXML
    private TableColumn<Result, Double> t5Col;
    @FXML
    private TableColumn<Result, Double> t6Col;
    @FXML
    private TableColumn<Result, Double> t7Col;
    @FXML
    private TableColumn<Result, Double> t8Col;
    @FXML
    private TableColumn<Result, Double> t9Col;
    @FXML
    private TableColumn<Result, Double> t10Col;
    @FXML
    private TableColumn<Result, Double> t11Col;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantifierChoicebox.getItems().addAll(quantifierNames);
        quantifierChoicebox.setOnAction(this::onQuantifierSelected);
        quantifierChoicebox.getSelectionModel().select(0);
        absoluteRadioButton.setOnAction(this::onAbsoluteSelected);
        relativeRadioButton.setOnAction(this::onRelativeSelected);

        linguisticVariableChoicebox.getItems().addAll(linguisticVariables);
        linguisticVariableChoicebox.getSelectionModel().select(0);
        onLinguisticVariableSelected(null);
        linguisticVariableChoicebox.setOnAction(this::onLinguisticVariableSelected);
        summarizerChoicebox.setOnAction(this::onSummarizerSelected);

        generateButton.setOnAction(this::onGenerate);

        summaryCol.setCellValueFactory(new PropertyValueFactory<Result, String>("summary"));
        tCol.setCellValueFactory(new PropertyValueFactory<Result, Double>("t"));
        t1Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t1"));
        t2Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t2"));
        t3Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t3"));
        t4Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t4"));
        t5Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t5"));
        t6Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t6"));
        t7Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t7"));
        t8Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t8"));
        t9Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t9"));
        t10Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t10"));
        t11Col.setCellValueFactory(new PropertyValueFactory<Result, Double>("t11"));

        tableView.setItems(results);
    }

    public void onLinguisticVariableSelected(ActionEvent event) {
        summarizerChoicebox.getItems().removeAll(summarizerLabelNames);
        String chosenLinguisticVariable = linguisticVariableChoicebox.getValue();
        summarizerLabelNames = Main.getLabelNames(chosenLinguisticVariable);
        summarizerChoicebox.getItems().addAll(summarizerLabelNames);
        summarizerChoicebox.getSelectionModel().select(0);
        onSummarizerSelected(null);
    }

    public void onSummarizerSelected(ActionEvent event) {
        chosenSummarizerName = summarizerChoicebox.getValue();
    }

    public void onQuantifierTypeChanged(String newType) {
        quantifierChoicebox.getItems().removeAll(quantifierNames);
        quantifierNames = Main.getQuantifierNames(newType);
        quantifierChoicebox.getItems().addAll(quantifierNames);
        quantifierChoicebox.getSelectionModel().select(0);
    }

    public void onAbsoluteSelected(ActionEvent event) {
        onQuantifierTypeChanged("absolute");
    }

    public void onRelativeSelected(ActionEvent event) {
        onQuantifierTypeChanged("relative");
    }

    private void onQuantifierSelected(ActionEvent event) {
        chosenQuantifierName = quantifierChoicebox.getValue();
    }

    private void onGenerate(ActionEvent event) {
        Result result = Main.generateSummary(chosenQuantifierName, chosenSummarizerName);
        results.add(result);

        //System.out.println(resultText);
        //System.out.println(results);
    }
}
