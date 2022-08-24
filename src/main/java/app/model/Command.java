package app.model;

public enum Command {
    ADD(2, "add"),
    LIST(0, "list"),
    FIND(1, "list"),
    RUNSPRINGBOOT(0, "runspringboot");
    private final int noOfParams;
    private final String actualCommand;

    Command(int noOfParams, String actualCommand) {
        this.noOfParams = noOfParams;
        this.actualCommand = actualCommand;
    }

    public int getNoOfParams() {
        return noOfParams;
    }

    public String getActualCommand() {
        return actualCommand;
    }
}
