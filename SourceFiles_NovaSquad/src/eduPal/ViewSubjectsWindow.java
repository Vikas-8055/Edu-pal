package eduPal;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ViewSubjectsWindow {

    public static void display(List<Subject> subjects, String studentName) {
        Stage window = new Stage();
        window.setTitle("Subjects " + studentName);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f0f8ff;");

        if (subjects == null || subjects.isEmpty()) {
            Label noSubjectsLabel = new Label("No subjects available " + studentName);
            noSubjectsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #FF0000;");
            layout.getChildren().add(noSubjectsLabel);
        } else {
            for (Subject subject : subjects) {
                Label subjectLabel = new Label(
                        "Subject: " + subject.getName() +
                        ", Grade: " + subject.getGrade() +
                        ", Threshold: " + subject.getThreshold()
                );
                subjectLabel.setStyle("-fx-font-size: 14px;");
                layout.getChildren().add(subjectLabel);
            }
        }

        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }
}