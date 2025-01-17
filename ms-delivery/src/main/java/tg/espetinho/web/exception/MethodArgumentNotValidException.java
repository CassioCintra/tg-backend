package tg.espetinho.web.exception;

public class MethodArgumentNotValidException extends RuntimeException {
   public MethodArgumentNotValidException(String message) {
      super(message);
   }
}
