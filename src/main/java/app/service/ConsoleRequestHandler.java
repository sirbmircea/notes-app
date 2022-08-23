package app.service;


public interface ConsoleRequestHandler {

    void runSpringBoot(String... args);

    void listCommand(String... params);

    void addCommand(String... params);
}
