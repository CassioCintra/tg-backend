package tg.espetinho.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
   @ExceptionHandler(EntityAlreadyExistsException.class)
   public ResponseEntity<ErrorMessage> entityExistsException (
           EntityAlreadyExistsException ex,
           HttpServletRequest request){
      return ResponseEntity.status(HttpStatus.CONFLICT)
              .contentType(MediaType.APPLICATION_JSON)
              .body(new ErrorMessage(request,
                      HttpStatus.CONFLICT,
                      ex.getMessage()));
   }

   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<ErrorMessage> entityNotFoundException (
           EntityNotFoundException ex,
           HttpServletRequest request){
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .contentType(MediaType.APPLICATION_JSON)
              .body(new ErrorMessage(request,
                      HttpStatus.NOT_FOUND,
                      ex.getMessage()));
   }

   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErrorMessage> handleBadRequest(
           MethodArgumentNotValidException ex,
           HttpServletRequest request) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
              .contentType(MediaType.APPLICATION_JSON)
              .body(new ErrorMessage(
                      request,
                      HttpStatus.BAD_REQUEST,
                      ex.getMessage())
              );
   }

}
