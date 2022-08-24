package app.model;

public class CliRequestObject {
    Command command;
    String[] args;
    boolean noOfArgsValid;

    public CliRequestObject(Command command, String[] args, boolean noOfArgsValid) {
        this.command = command;
        this.args = args;
        this.noOfArgsValid = noOfArgsValid;
    }

    public CliRequestObject(String[] args) {
        this.args = args;
    }

    public CliRequestObject() {

    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public boolean isNoOfArgsValid() {
        return noOfArgsValid;
    }

    public void setNoOfArgsValid(boolean noOfArgsValid) {
        this.noOfArgsValid = noOfArgsValid;
    }
}