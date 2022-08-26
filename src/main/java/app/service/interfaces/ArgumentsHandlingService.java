package app.service.interfaces;

import app.model.CliRequestObject;

import java.util.Optional;

public interface ArgumentsHandlingService {
    Optional<CliRequestObject> parse(String... args);

    void dispatch(Optional<CliRequestObject> optionalCliRequestObject);
}
