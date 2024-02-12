class Manager extends Human {
    private int id;
    private String loginUserName;
    private String loginPassword;

    public Manager(int id, String firstName, String lastName, String loginUserName, String loginPassword) {
        super(firstName, lastName);
        this.id = id;
        this.loginUserName = loginUserName;
        this.loginPassword = loginPassword;
    }

//    hello

    public void displayDetails() {
        System.out.println("Manager ID: " + id);
        System.out.println("Name: " + firstName + " " + lastName);
        System.out.println("Login Username: " + loginUserName);
        System.out.println("Login Password: " + loginPassword);
    }
}