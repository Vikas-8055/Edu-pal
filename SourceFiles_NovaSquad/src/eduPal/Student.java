package eduPal;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private List<Subject> subjects = new ArrayList<>();
    private double gradeThreshold;

    public Student(String name, double gradeThreshold) {
        this.name = name;
        this.gradeThreshold = gradeThreshold;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void checkGrades() {
        for (Subject subject : subjects) {
            if (subject.getGrade() < gradeThreshold) {
                new GradeNotification("Your grade in " + subject.getName() + " is below the threshold!").notifyStudent();
                provideMentalHealthResources();
            }
        }
    }

    // Use the interface to provide resources
    public void provideMentalHealthResources() {
        new MentalHealthResources().provideResources();
    }
}
