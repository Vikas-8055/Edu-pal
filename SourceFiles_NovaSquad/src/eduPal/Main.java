package eduPal;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    private Map<String, List<Subject>> studentSubjects = new HashMap<>();
    private Map<String, Stack<Subject>> studentActions = new HashMap<>();
    private String currentStudentName = "";

    @Override
    public void start(Stage primaryStage) {
        

        primaryStage.setTitle("Student Grade and Mental Health Tracker");

        
        Label titleLabel = new Label("Student Grade and Mental Health Tracker");
        titleLabel.getStyleClass().add("label-header");

       
        Label nameLabel = new Label("Enter your name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name...");
        nameField.getStyleClass().add("text-field");

 
        Label subjectLabel = new Label("Enter Subject Name:");
        TextField subjectField = new TextField();
        subjectField.setPromptText("Enter subject name...");
        subjectField.getStyleClass().add("text-field");

        Label gradeLabel = new Label("Enter your current grade:");
        TextField gradeField = new TextField();
        gradeField.setPromptText("Enter grade...");
        gradeField.getStyleClass().add("text-field");

        Label thresholdLabel = new Label("Enter your desired threshold:");
        TextField thresholdField = new TextField();
        thresholdField.setPromptText("Enter threshold...");
        thresholdField.getStyleClass().add("text-field");

       
        Button addSubjectButton = new Button("Add Subject");
        addSubjectButton.getStyleClass().add("button");

        Button viewSubjectsButton = new Button("View Subjects");
        viewSubjectsButton.getStyleClass().add("button");

        Button submitButton = new Button("Submit");
        submitButton.getStyleClass().add("button");

        Button undoButton = new Button("Undo Last Action");
        undoButton.getStyleClass().add("button");

        Button viewAllStudentsButton = new Button("View All Students");
        viewAllStudentsButton.getStyleClass().add("button");

        Button viewGraphButton = new Button("View Graph");
        viewGraphButton.getStyleClass().add("button");

        Button viewPieChartButton = new Button("View Pie Chart");
        viewPieChartButton.getStyleClass().add("button");

        Button removeSubjectButton = new Button("Remove Subject");
        removeSubjectButton.getStyleClass().add("button");

     
        nameField.setOnKeyTyped(e -> {
            String newStudentName = nameField.getText().trim();
            if (!newStudentName.equals(currentStudentName)) {
                currentStudentName = newStudentName;
                studentSubjects.putIfAbsent(currentStudentName, new ArrayList<>());
                studentActions.putIfAbsent(currentStudentName, new Stack<>());
            }
        });

        addSubjectButton.setOnAction(e -> {
            try {
                String subjectName = subjectField.getText();
                double grade = Double.parseDouble(gradeField.getText());
                double threshold = Double.parseDouble(thresholdField.getText());
                List<Subject> subjects = studentSubjects.getOrDefault(currentStudentName, new ArrayList<>());
                Stack<Subject> actions = studentActions.getOrDefault(currentStudentName, new Stack<>());
                Subject newSubject = new Subject(subjectName, grade, threshold);
                subjects.add(newSubject);
                actions.push(newSubject);
                studentSubjects.put(currentStudentName, subjects);
                studentActions.put(currentStudentName, actions);
                subjectField.clear();
                gradeField.clear();
                thresholdField.clear();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter valid numbers.");
            }
        });

        undoButton.setOnAction(e -> {
            List<Subject> subjects = studentSubjects.getOrDefault(currentStudentName, new ArrayList<>());
            Stack<Subject> actions = studentActions.getOrDefault(currentStudentName, new Stack<>());
            if (!actions.isEmpty()) {
                Subject lastAction = actions.pop();
                subjects.remove(lastAction);
                studentSubjects.put(currentStudentName, subjects);
                studentActions.put(currentStudentName, actions);
            }
        });

        viewSubjectsButton.setOnAction(e -> ViewSubjectsWindow.display(studentSubjects.get(currentStudentName), currentStudentName));
        viewAllStudentsButton.setOnAction(e -> ViewAllStudentsWindow.display(studentSubjects));
        viewGraphButton.setOnAction(e -> ViewGraphWindow.display(studentSubjects, currentStudentName));
        viewPieChartButton.setOnAction(e -> ViewPieChartWindow.display(studentSubjects, currentStudentName));

        submitButton.setOnAction(e -> SubmitWindow.display(studentSubjects.get(currentStudentName)));

        removeSubjectButton.setOnAction(e -> {
            if (currentStudentName.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Student Selected");
                alert.setHeaderText(null);
                alert.setContentText("Please enter a valid student name first.");
                alert.showAndWait();
            } else {
                RemoveSubjectWindow.display(studentSubjects, currentStudentName);
            }
        });

        
        GridPane inputGrid = new GridPane();
        inputGrid.setPadding(new Insets(20));
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        inputGrid.add(nameLabel, 0, 0);
        inputGrid.add(nameField, 1, 0);
        inputGrid.add(subjectLabel, 0, 1);
        inputGrid.add(subjectField, 1, 1);
        inputGrid.add(gradeLabel, 0, 2);
        inputGrid.add(gradeField, 1, 2);
        inputGrid.add(thresholdLabel, 0, 3);
        inputGrid.add(thresholdField, 1, 3);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setPadding(new Insets(20));
        buttonGrid.setHgap(10);
        buttonGrid.setVgap(10);

        buttonGrid.add(addSubjectButton, 0, 0);
        buttonGrid.add(viewSubjectsButton, 1, 0);
        buttonGrid.add(submitButton, 2, 0);
        buttonGrid.add(undoButton, 3, 0);
        buttonGrid.add(viewAllStudentsButton, 0, 1);
        buttonGrid.add(viewGraphButton, 1, 1);
        buttonGrid.add(viewPieChartButton, 2, 1);
        buttonGrid.add(removeSubjectButton, 3, 1);

        VBox layout = new VBox(20);
        layout.getChildren().addAll(titleLabel, inputGrid, buttonGrid);

        Scene mainScene = new Scene(layout, 600, 600);
        mainScene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
       
        loginPage.launch(args);
    }
}