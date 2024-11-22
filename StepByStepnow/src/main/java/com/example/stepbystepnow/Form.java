package com.example.stepbystepnow;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Form extends Application {
    private List<Person> personList = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        // Show the Welcome Screen
        showWelcomeScreen(stage);
    }

    private void showWelcomeScreen(Stage stage) {
        VBox welcomeBox = new VBox(20);
        welcomeBox.setAlignment(Pos.CENTER);
        welcomeBox.setPadding(new Insets(20));

        Label welcomeLabel = new Label("WELCOME TO ADMISSION FORM");
        welcomeLabel.setFont(new Font("Arial", 24));
        welcomeLabel.setTextFill(Color.DARKBLUE);

        Button registerButton = new Button("Register");
        Button exitButton = new Button("Exit");

        registerButton.setOnAction(e -> showAdmissionForm(stage));
        exitButton.setOnAction(e -> stage.close());

        welcomeBox.getChildren().addAll(welcomeLabel, registerButton, exitButton);

        Scene welcomeScene = new Scene(welcomeBox, 500, 200);
        stage.setScene(welcomeScene);
        stage.setTitle("Welcome");
        stage.show();
    }

    private void showAdmissionForm(Stage stage) {
        // Create the root container (BorderPane)
        BorderPane root = new BorderPane();

        // bannerr
        Label bannerLabel = new Label("Admission Form");
        bannerLabel.setFont(new Font("New Times Roman", 24));
        bannerLabel.setTextFill(Color.DARKBLUE);
        HBox bannerBox = new HBox(bannerLabel);
        bannerBox.setAlignment(Pos.CENTER);
        bannerBox.setPadding(new Insets(10));
        root.setTop(bannerBox);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(40);
        gridPane.setHgap(40);

        // Name Field
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();
        gridPane.add(nameLabel, 1, 1);
        gridPane.add(nameField, 2, 1);

        // Fathaaaaa
        Label fathaNameLabel = new Label("Father's Name:");
        TextField fathaNameField = new TextField();
        gridPane.add(fathaNameLabel, 1, 2);
        gridPane.add(fathaNameField, 2, 2);

        // CNIC
        Label cnicLabel = new Label("CNIC:");
        TextField cnicField = new TextField();
        gridPane.add(cnicLabel, 1, 3);
        gridPane.add(cnicField, 2, 3);

        // Date of Birth Field
        Label dobLabel = new Label("Date of Birth:");
        DatePicker dobPicker = new DatePicker();
        gridPane.add(dobLabel, 1, 4);
        gridPane.add(dobPicker, 2, 4);

        // Gender
        Label genderLabel = new Label("Gender:");
        RadioButton maleButton = new RadioButton("Male");
        RadioButton femaleButton = new RadioButton("Female");
        RadioButton otherButton = new RadioButton("Suspicious");
        // rRadio Buttons
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
        otherButton.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleButton, femaleButton, otherButton);
        gridPane.add(genderLabel, 1, 5);
        gridPane.add(genderBox, 2, 5);

        // Cityyy
        Label cityLabel = new Label("City:");
        ComboBox<String> cityComboBox = new ComboBox<>();
        cityComboBox.getItems().addAll("Karachi", "Lahore", "Islamabad", "Quetta", "Peshawar");
        cityComboBox.setPromptText("Select your city");
        gridPane.add(cityLabel, 1, 6);
        gridPane.add(cityComboBox, 2, 6);


        Button saveButton = new Button("Save");
        gridPane.add(saveButton, 2, 7);
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String fathaName = fathaNameField.getText();
            String cnic = cnicField.getText();
            String dob = (dobPicker.getValue() != null) ? dobPicker.getValue().toString() : "";
            String gender = maleButton.isSelected() ? "Male" :
                    femaleButton.isSelected() ? "Female" :
                            otherButton.isSelected() ? "Suspicious" : "";
            String city = cityComboBox.getValue();


            Person person = new Person(name, fathaName, cnic, dob, gender, city);
            personList.add(person);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Saved");
            alert.setHeaderText("Form Saved Successfully");
            alert.setContentText("You have saved the form for " + name + ".");
            alert.showAndWait();

            nameField.clear();
            fathaNameField.clear();
            cnicField.clear();
            dobPicker.setValue(null);
            genderGroup.selectToggle(null);
            cityComboBox.setValue(null);
        });


        Button exitButton = new Button("Exit");
        gridPane.add(exitButton, 1, 7);
        exitButton.setOnAction(e -> stage.close());

        root.setCenter(gridPane);

        VBox rightPane = new VBox(10);
        rightPane.setPadding(new Insets(20));
        rightPane.setAlignment(Pos.TOP_CENTER);

        Label imageLabel = new Label("Upload Image:");
        Button uploadButton = new Button("Choose Image");
        ImageView imageView = new ImageView();
        imageView.setFitHeight(150);
        imageView.setFitWidth(150);
        imageView.setPreserveRatio(true);

        uploadButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select an Image");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image);
            }
        });

        rightPane.getChildren().addAll(imageLabel, uploadButton, imageView);
        root.setRight(rightPane);

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("Admission Form");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
