package stockMarketChartingApp.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	/*
	 * @ExceptionHandler(ResourceNotFoundException.class) public
	 * ResponseEntity<String>
	 * handleResourceNotFoundException(ResourceNotFoundException ex) { return new
	 * ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); }
	 * 
	 * @ExceptionHandler(Exception.class) public ResponseEntity<String>
	 * handleGlobalException(Exception ex) { return new
	 * ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */
@ExceptionHandler(MethodArgumentNotValidException.class)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> 
        errors.put(error.getField(), error.getDefaultMessage()));
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(ResourceNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
}
}
