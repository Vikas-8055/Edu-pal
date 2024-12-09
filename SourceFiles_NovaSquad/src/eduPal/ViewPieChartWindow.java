package eduPal;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class ViewPieChartWindow {

    public static void display(Map<String, List<Subject>> studentSubjects, String studentName) {
        Stage window = new Stage();
        window.setTitle("Grade Distribution " + studentName);

        List<Subject> subjects = studentSubjects.getOrDefault(studentName, null);

        if (subjects == null || subjects.isEmpty()) {
            VBox layout = new VBox(10);
            layout.setPadding(new Insets(20));
            layout.setStyle("-fx-background-color: #f0f8ff;");

            javafx.scene.control.Label noDataLabel = new javafx.scene.control.Label("No subjects available for " + studentName);
            noDataLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #FF0000;");
            layout.getChildren().add(noDataLabel);

            Scene scene = new Scene(layout, 400, 200);
            window.setScene(scene);
            window.show();
            return;
        }

        PieChart pieChart = new PieChart();
        pieChart.setTitle("Grade Distribution");

        for (Subject subject : subjects) {
            PieChart.Data slice = new PieChart.Data(subject.getName(), subject.getGrade());
            pieChart.getData().add(slice);
        }

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().add(pieChart);

        Scene scene = new Scene(layout, 600, 400);
        window.setScene(scene);
        window.show();
    }
}