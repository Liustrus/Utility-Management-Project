abstract class Human {
    protected String firstName;
    protected String lastName;

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public abstract void displayDetails();
}