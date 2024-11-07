package core;

public class Customer {
    private String name;
    private boolean validID;

    public Customer(String name, boolean validID) {
        this.name = name;
        this.validID = validID;
    }

    public boolean hasValidID() {
        return validID;
    }

    public String getName() {
        return name;
    }
}
