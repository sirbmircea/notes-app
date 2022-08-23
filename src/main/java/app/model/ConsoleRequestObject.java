package app.model;

public class ConsoleRequestObject {
    private Command command;
    private String[] params;
    private String[] args;

    public ConsoleRequestObject(Command command, String[] params, String[] args) {
        this.command = command;
        this.params = params;
        this.args = args;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }
}
