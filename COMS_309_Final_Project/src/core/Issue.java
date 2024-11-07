package core;

public class Issue {
    private String description;
    private boolean requiresIDVerification;

    public Issue(String description, boolean requiresIDVerification) {
        this.description = description;
        this.requiresIDVerification = requiresIDVerification;
    }

    public boolean requiresIDVerification() {
        return requiresIDVerification;
    }

    public String getDescription() {
        return description;
    }
}
