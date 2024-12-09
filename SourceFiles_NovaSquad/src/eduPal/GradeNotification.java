package eduPal;

class GradeNotification extends Notification {
    private String message;
    
    public GradeNotification(String message) {
        this.message = message;
    }

    @Override
    void notifyStudent() {
        System.out.println("Notification: " + message);
    }
}
