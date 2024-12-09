package eduPal;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label; // Import for Label
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class ViewGraphWindow {

    public static void display(Map<String, List<Subject>> studentSubjects, String studentName) {
        Stage window = new Stage();
        window.setTitle("Grade Comparison for " + studentName);

        // Retrieve the student's subjects
        List<Subject> subjects = studentSubjects.getOrDefault(studentName, null);

        if (subjects == null || subjects.isEmpty()) {
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(20));
            layout.setStyle("-fx-background-color: #f0f8ff;");

            Label noSubjectsLabel = new Label("No subjects available " + studentName);
            noSubjectsLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #FF0000;");
            layout.getChildren().add(noSubjectsLabel);

            Scene scene = new Scene(layout, 400, 200);
            window.setScene(scene);
            window.show();
            return;
        }

        // Create the x-axis and y-axis
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Subjects");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Grades");

        // Create the BarChart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Current Grade vs Threshold Grade");

        // Data Series for Current Grades
        XYChart.Series<String, Number> currentGradeSeries = new XYChart.Series<>();
        currentGradeSeries.setName("Current Grade");

        // Data Series for Threshold Grades
        XYChart.Series<String, Number> thresholdGradeSeries = new XYChart.Series<>();
        thresholdGradeSeries.setName("Threshold Grade");

        // Populate the data series
        for (Subject subject : subjects) {
            currentGradeSeries.getData().add(new XYChart.Data<>(subject.getName(), subject.getGrade()));
            thresholdGradeSeries.getData().add(new XYChart.Data<>(subject.getName(), subject.getThreshold()));
        }

        // Add data to the chart
        barChart.getData().addAll(currentGradeSeries, thresholdGradeSeries);

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().add(barChart);

        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        window.show();
    }
}