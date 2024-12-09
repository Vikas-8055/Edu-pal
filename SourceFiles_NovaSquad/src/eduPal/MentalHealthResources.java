package eduPal;

class MentalHealthResources implements Resource {
    @Override
    public void provideResources() {
        System.out.println("Here are some mental health resources: ");
        System.out.println("1. Talk to a counselor.");
        System.out.println("2. Take a short break.");
        System.out.println("3. Practice meditation.");
    }
}

