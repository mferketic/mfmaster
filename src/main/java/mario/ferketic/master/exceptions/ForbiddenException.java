package mario.ferketic.master.exceptions;

import lombok.NonNull;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException(@NonNull String message) {
        super(message);
    }

}
