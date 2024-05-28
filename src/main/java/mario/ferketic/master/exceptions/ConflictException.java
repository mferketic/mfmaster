package mario.ferketic.master.exceptions;

import lombok.NonNull;

public class ConflictException extends RuntimeException {

    public ConflictException(@NonNull String message) {
        super(message);
    }

}
