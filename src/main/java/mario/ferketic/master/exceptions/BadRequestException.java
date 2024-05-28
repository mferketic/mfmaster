package mario.ferketic.master.exceptions;

import lombok.NonNull;

public class BadRequestException extends RuntimeException {

    public BadRequestException(@NonNull String message) {
        super(message);
    }

}
