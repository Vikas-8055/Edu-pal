package eduPal;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class RemoveSubjectWindow {

    public static void display(Map<String, List<Subject>> studentSubjects, String currentStudentName) {
        Stage window = new Stage();
        window.setTitle("Remove Subject");

        Label instructionLabel = new Label("Enter the name of the subject to remove:");
        TextField subjectField = new TextField();
        subjectField.setPromptText("Enter subject name...");

        Button removeButton = new Button("Remove");
        Label messageLabel = new Label();

        removeButton.setOnAction(e -> {
            String subjectName = subjectField.getText().trim();
            List<Subject> subjects = studentSubjects.getOrDefault(currentStudentName, null);

            if (subjects == null || subjects.isEmpty()) {
                messageLabel.setText("No subjects available " + currentStudentName);
                messageLabel.setStyle("-fx-text-fill: red;");
                return;
            }

            boolean removed = subjects.removeIf(subject -> subject.getName().equalsIgnoreCase(subjectName));
            if (removed) {
                messageLabel.setText("Subject removed successfully.");
                messageLabel.setStyle("-fx-text-fill: green;");
                subjectField.clear();
            } else {
                messageLabel.setText("Subject not found.");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(instructionLabel, subjectField, removeButton, messageLabel);

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }
}