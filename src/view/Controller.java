package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final ArrayList<String> linguisticVariables = Main.getLinguisticVariableNames();

    private final ArrayList<ArrayList<String>> allSummarizerLabelNames = new ArrayList<>();
    private final ArrayList<String> chosenSummarizerNames = new ArrayList<>();
    private int numberOfSummarizers = 0;
    private final ObservableList<ChoiceBox<String>> linguisticVariableChoiceBoxes = FXCollections.observableArrayList();
    private final ObservableList<ChoiceBox<String>> summarizerChoiceBoxes = FXCollections.observableArrayList();
    private final Pane mainPane = new Pane();

    private ArrayList<String> quantifierNames = Main.getQuantifierNames("absolute");
    private String chosenQuantifierName;
    private final ObservableList<Result> results = FXCollections.observableArrayList();

    private final ArrayList<ArrayList<String>> allQualifierSummarizerLabelNames = new ArrayList<>();
    private final ArrayList<String> chosenQualifierNames = new ArrayList<>();
    private int numberOfQualifiers = 0;
    private final ObservableList<ChoiceBox<String>> qualifierLinguisticVariableChoiceBoxes = FXCollections.observableArrayList();
    private final ObservableList<ChoiceBox<String>> qualifierSummarizerChoiceBoxes = FXCollections.observableArrayList();
    private final Pane qualifierMainPane = new Pane();

    private final ArrayList<String> countryList = new ArrayList<>();
    private final ArrayList<String> formList = new ArrayList<>();

    @FXML
    private RadioButton absoluteRadioButton;
    @FXML
    private RadioButton relativeRadioButton;
    @FXML
    private ChoiceBox<String> quantifierChoicebox;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ScrollPane qualifierScrollPane;

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

    @FXML
    private Button addSummarizerButton;
    @FXML
    private Button removeSummarizerButton;

    @FXML
    private Button addQualifierButton;
    @FXML
    private Button removeQualifierButton;

    @FXML
    private TextField textField;

    @FXML
    private CheckBox multiSubjectCheckbox;
    @FXML
    private ChoiceBox<String> firstSubjectChoicebox;
    @FXML
    private ChoiceBox<String> secondSubjectChoicebox;
    @FXML
    private ChoiceBox<String> formChoicebox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quantifierChoicebox.getItems().addAll(quantifierNames);
        quantifierChoicebox.setOnAction(this::onQuantifierSelected);
        quantifierChoicebox.getSelectionModel().select(0);
        absoluteRadioButton.setOnAction(this::onAbsoluteSelected);
        relativeRadioButton.setOnAction(this::onRelativeSelected);

        addSummarizer();

        generateButton.setOnAction(this::onGenerate);

        summaryCol.setCellValueFactory(new PropertyValueFactory<>("summary"));
        tCol.setCellValueFactory(new PropertyValueFactory<>("t"));
        t1Col.setCellValueFactory(new PropertyValueFactory<>("t1"));
        t2Col.setCellValueFactory(new PropertyValueFactory<>("t2"));
        t3Col.setCellValueFactory(new PropertyValueFactory<>("t3"));
        t4Col.setCellValueFactory(new PropertyValueFactory<>("t4"));
        t5Col.setCellValueFactory(new PropertyValueFactory<>("t5"));
        t6Col.setCellValueFactory(new PropertyValueFactory<>("t6"));
        t7Col.setCellValueFactory(new PropertyValueFactory<>("t7"));
        t8Col.setCellValueFactory(new PropertyValueFactory<>("t8"));
        t9Col.setCellValueFactory(new PropertyValueFactory<>("t9"));
        t10Col.setCellValueFactory(new PropertyValueFactory<>("t10"));
        t11Col.setCellValueFactory(new PropertyValueFactory<>("t11"));

        tableView.setItems(results);

        addSummarizerButton.setOnAction((event -> addSummarizer()));
        removeSummarizerButton.setOnAction((event -> removeSummarizer()));
        removeSummarizerButton.setDisable(true);

        addQualifierButton.setOnAction((event -> addQualifier()));
        removeQualifierButton.setOnAction((event -> removeQualifier()));
        removeQualifierButton.setDisable(true);

        textField.setText("1;1;1;1;1;1;1;1;1;1;1");

        countryList.add("ENGLAND");
        countryList.add("GERMANY");
        countryList.add("SPAIN");
        countryList.add("ARGENTINA");
        countryList.add("FRANCE");
        countryList.add("BRAZIL");
        countryList.add("ITALY");
        countryList.add("COLOMBIA");
        countryList.add("JAPAN");
        countryList.add("NETHERLANDS");
        countryList.add("SWEDEN");
        countryList.add("CHINA PR");
        countryList.add("CHILE");
        countryList.add("REPUBLIC OF IRELAND");
        countryList.add("MEXICO");
        countryList.add("UNITED STATES");
        countryList.add("POLAND");
        countryList.add("NORWAY");
        countryList.add("SAUDI ARABIA");
        countryList.add("DENMARK");
        countryList.add("KOREA REPUBLIC");
        countryList.add("PORTUGAL");
        countryList.add("TURKEY");
        countryList.add("AUSTRIA");
        countryList.add("SCOTLAND");
        countryList.add("BELGIUM");

        firstSubjectChoicebox.getItems().addAll(countryList);
        firstSubjectChoicebox.getSelectionModel().select(0);
        secondSubjectChoicebox.getItems().addAll(countryList);
        secondSubjectChoicebox.getSelectionModel().select(1);
        firstSubjectChoicebox.setDisable(true);
        secondSubjectChoicebox.setDisable(true);
        multiSubjectCheckbox.setOnAction(this::onMultiCheckboxChanged);

        formList.add("Pierwsza forma");
        formList.add("Druga forma");
        formList.add("Trzecia forma");
        formList.add("Czwarta forma");
        formChoicebox.getItems().addAll(formList);
        formChoicebox.getSelectionModel().select(0);
        formChoicebox.setDisable(true);
    }

    public void onLinguisticVariableSelected(ActionEvent event, int index) {
        ChoiceBox<String> summarizerChoiceBox = summarizerChoiceBoxes.get(index);
        ChoiceBox<String> linguisticVariableChoiceBox = linguisticVariableChoiceBoxes.get(index);
        ArrayList<String> summarizerLabelNames = allSummarizerLabelNames.get(index);

        summarizerChoiceBox.getItems().removeAll(summarizerLabelNames);
        String chosenLinguisticVariable = linguisticVariableChoiceBox.getValue();
        summarizerLabelNames = Main.getLabelNames(chosenLinguisticVariable);
        summarizerChoiceBox.getItems().addAll(summarizerLabelNames);
        summarizerChoiceBox.getSelectionModel().select(0);
        summarizerChoiceBoxes.set(index, summarizerChoiceBox);
        allSummarizerLabelNames.set(index, summarizerLabelNames);
        onSummarizerSelected(null, index);
    }

    public void onSummarizerSelected(ActionEvent event, int index) {
        ChoiceBox<String> summarizerChoiceBox = summarizerChoiceBoxes.get(index);
        chosenSummarizerNames.set(index, summarizerChoiceBox.getValue());
    }

    public void onQualifierLinguisticVariableSelected(ActionEvent event, int index) {
        ChoiceBox<String> qualifierSummarizerChoiceBox = qualifierSummarizerChoiceBoxes.get(index);
        ChoiceBox<String> qualifierLinguisticVariableChoiceBox = qualifierLinguisticVariableChoiceBoxes.get(index);
        ArrayList<String> qualifierSummarizerLabelNames = allQualifierSummarizerLabelNames.get(index);

        qualifierSummarizerChoiceBox.getItems().removeAll(qualifierSummarizerLabelNames);
        String chosenLinguisticVariable = qualifierLinguisticVariableChoiceBox.getValue();
        qualifierSummarizerLabelNames = Main.getLabelNames(chosenLinguisticVariable);
        qualifierSummarizerChoiceBox.getItems().addAll(qualifierSummarizerLabelNames);
        qualifierSummarizerChoiceBox.getSelectionModel().select(0);
        qualifierSummarizerChoiceBoxes.set(index, qualifierSummarizerChoiceBox);
        allQualifierSummarizerLabelNames.set(index, qualifierSummarizerLabelNames);
        onQualifierSelected(null, index);
    }

    public void onQualifierSelected(ActionEvent event, int index) {
        ChoiceBox<String> qualifierSummarizerChoiceBox = qualifierSummarizerChoiceBoxes.get(index);
        chosenQualifierNames.set(index, qualifierSummarizerChoiceBox.getValue());
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
        // Multi subject
        String firstChosenSubject = "";
        String secondChosenSubject = "";
        int chosenForm = -1;
        if(multiSubjectCheckbox.isSelected()) {
            firstChosenSubject = firstSubjectChoicebox.getValue();
            secondChosenSubject = secondSubjectChoicebox.getValue();
            chosenForm = formChoicebox.getSelectionModel().getSelectedIndex() + 1;
        }

        // Weights
        String[] weightsStr = textField.getText().split(";");
        ArrayList<Double> weights = new ArrayList<>();
        for(String value : weightsStr) {
            weights.add(Double.parseDouble(value));
        }

        // Generate result
        Result result = Main.generateSummary(chosenQuantifierName, chosenSummarizerNames,
                chosenQualifierNames, weights, firstChosenSubject, secondChosenSubject, chosenForm);
        results.add(result);
    }

    private void addSummarizer() {
        allSummarizerLabelNames.add(new ArrayList<>());
        chosenSummarizerNames.add("");

        Label linguisticVariableLabel = new Label();
        linguisticVariableLabel.setText((numberOfSummarizers + 1) + ". Zmienna lingwistyczna");
        linguisticVariableLabel.setTranslateX(75);
        linguisticVariableLabel.setTranslateY(5 + numberOfSummarizers * 140);
        mainPane.getChildren().add(linguisticVariableLabel);

        ChoiceBox<String> linguisticVariableChoiceBox = new ChoiceBox<>();
        linguisticVariableChoiceBox.setPrefWidth(260);
        linguisticVariableChoiceBox.setTranslateX(14);
        linguisticVariableChoiceBox.setTranslateY(30 + numberOfSummarizers * 140);
        linguisticVariableChoiceBox.getItems().addAll(linguisticVariables);
        linguisticVariableChoiceBox.getSelectionModel().select(0);
        int finalNr = numberOfSummarizers;
        linguisticVariableChoiceBox.setOnAction((event -> onLinguisticVariableSelected(event, finalNr)));
        linguisticVariableChoiceBoxes.add(linguisticVariableChoiceBox);
        mainPane.getChildren().add(linguisticVariableChoiceBoxes.get(numberOfSummarizers));

        Label summarizerLabel = new Label();
        summarizerLabel.setText("Sumaryzator");
        summarizerLabel.setTranslateX(110);
        summarizerLabel.setTranslateY(65 + numberOfSummarizers * 140);
        mainPane.getChildren().add(summarizerLabel);

        ChoiceBox<String> summarizerChoiceBox = new ChoiceBox<>();
        summarizerChoiceBox.setPrefWidth(260);
        summarizerChoiceBox.setTranslateX(14);
        summarizerChoiceBox.setTranslateY(90 + numberOfSummarizers * 140);
        //summarizerChoiceBox.getItems().addAll(linguisticVariables);
        summarizerChoiceBox.getSelectionModel().select(0);
        summarizerChoiceBox.setOnAction((event -> onSummarizerSelected(event, finalNr)));
        summarizerChoiceBoxes.add(summarizerChoiceBox);
        mainPane.getChildren().add(summarizerChoiceBoxes.get(numberOfSummarizers));

        onLinguisticVariableSelected(null, numberOfSummarizers);
        onSummarizerSelected(null, numberOfSummarizers);

        int height = 120 + numberOfSummarizers * 140;
        mainPane.setPrefHeight(Math.max(height, 250));
        scrollPane.setContent(mainPane);

        numberOfSummarizers++;
        removeSummarizerButton.setDisable(false);
    }

    public void removeSummarizer() {
        int index = numberOfSummarizers - 1;
        allSummarizerLabelNames.remove(index);
        chosenSummarizerNames.remove(index);
        linguisticVariableChoiceBoxes.remove(index);
        summarizerChoiceBoxes.remove(index);
        int numberOfChildren = mainPane.getChildren().size();
        for(int i = 1; i < 5; i++) {
            mainPane.getChildren().remove(numberOfChildren - i);
        }
        numberOfSummarizers--;
        int height = 120 + (numberOfSummarizers - 1) * 140;
        mainPane.setPrefHeight(Math.max(height, 250));
        if(numberOfSummarizers < 2) {
            removeSummarizerButton.setDisable(true);
        }
    }

    private void addQualifier() {
        allQualifierSummarizerLabelNames.add(new ArrayList<>());
        chosenQualifierNames.add("");

        Label linguisticVariableLabel = new Label();
        linguisticVariableLabel.setText((numberOfQualifiers + 1) + ". Zmienna lingwistyczna");
        linguisticVariableLabel.setTranslateX(75);
        linguisticVariableLabel.setTranslateY(5 + numberOfQualifiers * 140);
        qualifierMainPane.getChildren().add(linguisticVariableLabel);

        ChoiceBox<String> linguisticVariableChoiceBox = new ChoiceBox<>();
        linguisticVariableChoiceBox.setPrefWidth(260);
        linguisticVariableChoiceBox.setTranslateX(14);
        linguisticVariableChoiceBox.setTranslateY(30 + numberOfQualifiers * 140);
        linguisticVariableChoiceBox.getItems().addAll(linguisticVariables);
        linguisticVariableChoiceBox.getSelectionModel().select(0);
        int finalNr = numberOfQualifiers;
        linguisticVariableChoiceBox.setOnAction((event -> onQualifierLinguisticVariableSelected(event, finalNr)));
        qualifierLinguisticVariableChoiceBoxes.add(linguisticVariableChoiceBox);
        qualifierMainPane.getChildren().add(qualifierLinguisticVariableChoiceBoxes.get(numberOfQualifiers));

        Label summarizerLabel = new Label();
        summarizerLabel.setText("Kwalifikator");
        summarizerLabel.setTranslateX(110);
        summarizerLabel.setTranslateY(65 + numberOfQualifiers * 140);
        qualifierMainPane.getChildren().add(summarizerLabel);

        ChoiceBox<String> summarizerChoiceBox = new ChoiceBox<>();
        summarizerChoiceBox.setPrefWidth(260);
        summarizerChoiceBox.setTranslateX(14);
        summarizerChoiceBox.setTranslateY(90 + numberOfQualifiers * 140);
        //summarizerChoiceBox.getItems().addAll(linguisticVariables);
        summarizerChoiceBox.getSelectionModel().select(0);
        summarizerChoiceBox.setOnAction((event -> onQualifierSelected(event, finalNr)));
        qualifierSummarizerChoiceBoxes.add(summarizerChoiceBox);
        qualifierMainPane.getChildren().add(qualifierSummarizerChoiceBoxes.get(numberOfQualifiers));

        onQualifierLinguisticVariableSelected(null, numberOfQualifiers);
        onQualifierSelected(null, numberOfQualifiers);

        int height = 120 + numberOfQualifiers * 140;
        qualifierMainPane.setPrefHeight(Math.max(height, 250));
        qualifierScrollPane.setContent(qualifierMainPane);

        numberOfQualifiers++;
        removeQualifierButton.setDisable(false);
    }

    public void removeQualifier() {
        int index = numberOfQualifiers - 1;
        allQualifierSummarizerLabelNames.remove(index);
        chosenQualifierNames.remove(index);
        qualifierLinguisticVariableChoiceBoxes.remove(index);
        qualifierSummarizerChoiceBoxes.remove(index);
        int numberOfChildren = qualifierMainPane.getChildren().size();
        for(int i = 1; i < 5; i++) {
            qualifierMainPane.getChildren().remove(numberOfChildren - i);
        }
        numberOfQualifiers--;
        int height = 120 + (numberOfQualifiers - 1) * 140;
        qualifierMainPane.setPrefHeight(Math.max(height, 250));
        if(numberOfQualifiers < 1) {
            removeQualifierButton.setDisable(true);
        }
    }

    public void onMultiCheckboxChanged(ActionEvent event) {
        if(multiSubjectCheckbox.isSelected()) {
            firstSubjectChoicebox.setDisable(false);
            secondSubjectChoicebox.setDisable(false);
            formChoicebox.setDisable(false);
            relativeRadioButton.setSelected(true);
            absoluteRadioButton.setDisable(true);
            onRelativeSelected(null);
        }
        else {
            firstSubjectChoicebox.setDisable(true);
            secondSubjectChoicebox.setDisable(true);
            formChoicebox.setDisable(true);
            absoluteRadioButton.setDisable(false);
        }
    }
}
