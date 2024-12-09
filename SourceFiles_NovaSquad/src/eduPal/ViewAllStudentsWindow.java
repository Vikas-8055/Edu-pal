package eduPal;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class ViewAllStudentsWindow {

    public static void display(Map<String, List<Subject>> studentSubjects) {
        Stage window = new Stage();
        window.setTitle("All Students with Subjects");

        // Main layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f0f8ff;");

        boolean hasStudentsWithSubjects = false;

        // Add details for each student
        for (Map.Entry<String, List<Subject>> entry : studentSubjects.entrySet()) {
            String studentName = entry.getKey();
            List<Subject> subjects = entry.getValue();

            if (!subjects.isEmpty()) {
                hasStudentsWithSubjects = true;

                // Add student name
                Label studentLabel = new Label("Student: " + studentName);
                studentLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");

                // Add subjects for the student
                VBox subjectLayout = new VBox(5);
                subjectLayout.setStyle("-fx-border-color: #ccc; -fx-border-radius: 5; -fx-padding: 10;");

                for (Subject subject : subjects) {
                    Label subjectLabel = new Label(
                            "Subject: " + subject.getName() +
                            ", Grade: " + subject.getGrade() +
                            ", Threshold: " + subject.getThreshold()
                    );
                    subjectLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
                    subjectLayout.getChildren().add(subjectLabel);
                }

                layout.getChildren().addAll(studentLabel, subjectLayout);
            }
        }

        if (!hasStudentsWithSubjects) {
            Label noStudentsLabel = new Label("No students with subjects available.");
            noStudentsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #FF0000;");
            layout.getChildren().add(noStudentsLabel);
        }

        // Scroll pane for long lists
        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);

        Scene scene = new Scene(scrollPane, 400, 500);
        window.setScene(scene);
        window.show();
    }
}