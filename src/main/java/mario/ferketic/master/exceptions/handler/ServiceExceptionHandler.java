package mario.ferketic.master.exceptions.handler;

import mario.ferketic.master.exceptions.BadRequestException;
import mario.ferketic.master.exceptions.ConflictException;
import mario.ferketic.master.exceptions.ForbiddenException;
import org.springframework.core.annotation.Order;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.StringJoiner;

@ControllerAdvice
public class ServiceExceptionHandler {

    public static final String MSG_BAD_REQUEST = "Bad request";
    public static final String MSG_CONFLICT = "Conflict";
    public static final String MSG_FORBIDDEN = "Forbidden";
    public static final String MSG_INTERNAL_SERVER_ERROR = "Internal server error";

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Order(10)
    protected ResponseEntity<Problem> handleBeanValidation(BindException exception) {
        return getEmployeesServiceErrorResponse(HttpStatus.BAD_REQUEST, MSG_BAD_REQUEST, getBindingResultMessages(exception));
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Order(11)
    protected ResponseEntity<Problem> handleBadRequest(BadRequestException exception) {
        return getEmployeesServiceErrorResponse(HttpStatus.BAD_REQUEST, MSG_BAD_REQUEST, exception.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @Order(99)
    protected ResponseEntity<Problem> handleInternalServerError(RuntimeException exception) {
        System.out.println(exception);
        return getEmployeesServiceErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, MSG_INTERNAL_SERVER_ERROR, "Problem occurred during runtime");
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    @Order(100)
    protected ResponseEntity<Problem> handleConflictException(ConflictException exception) {
        return getEmployeesServiceErrorResponse(HttpStatus.CONFLICT, MSG_CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    ResponseEntity<Problem> handleForbidden(ForbiddenException exception) {
        return getEmployeesServiceErrorResponse(HttpStatus.FORBIDDEN, MSG_FORBIDDEN, exception.getMessage());
    }


    private ResponseEntity<Problem> getEmployeesServiceErrorResponse(HttpStatus status, String errorMessage, String errorDetail) {
        return ResponseEntity.status(status)
                .body(Problem.create()
                        .withTitle(errorMessage)
                        .withDetail(errorDetail)
                        .withStatus(status)
                );
    }

    private String getBindingResultMessages(BindException exception) {
        StringJoiner joiner = new StringJoiner(", ");
        exception.getBindingResult().getAllErrors().forEach(error -> {
            if (exception.getBindingResult().getErrorCount() > 1) {
                joiner.add("[" + error.getDefaultMessage() + "]");
            } else {
                joiner.add(error.getDefaultMessage());
            }
        });
        return joiner.toString();
    }
}
