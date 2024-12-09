package eduPal;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class SubmitWindow {

    public static void display(List<Subject> subjects) {
        Stage window = new Stage();
        window.setTitle("Grade Report");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #f0f8ff;");

        for (Subject subject : subjects) {
            Label messageLabel;

            if (subject.getGrade() >= subject.getThreshold()) {
                messageLabel = new Label(subject.getName() + ": Good job, you are doing great!");
                messageLabel.setStyle("-fx-text-fill: green; -fx-font-size: 14px;");
            } else {
                messageLabel = new Label(subject.getName() + ": Current grade is lesser than your desired threshold.");
                messageLabel.setStyle("-fx-text-fill: red; -fx-font-size: 14px;");

                // Buttons for resources
                Button improveGradeButton = new Button("Improve Grade");
                improveGradeButton.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white;");
                improveGradeButton.setOnAction(e -> openPDF("Improve_Grade.pdf"));

                Button mentalHealthButton = new Button("Mental Health Resources");
                mentalHealthButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white;");
                mentalHealthButton.setOnAction(e -> openPDF("Mental_Health_Resources.pdf"));

                layout.getChildren().addAll(improveGradeButton, mentalHealthButton);
            }

            layout.getChildren().add(messageLabel);
        }

        Scene scene = new Scene(layout, 400, 400);
        window.setScene(scene);
        window.show();
    }

    private static void openPDF(String fileName) {
        try {
            Desktop.getDesktop().open(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
