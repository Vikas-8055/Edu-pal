package eduPal;

public class Subject {
    private String name;
    private double grade;
    private double threshold;

    public Subject(String name, double grade, double threshold) {
        this.name = name;
        this.grade = grade;
        this.threshold = threshold;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public double getThreshold() {
        return threshold;
    }
}
