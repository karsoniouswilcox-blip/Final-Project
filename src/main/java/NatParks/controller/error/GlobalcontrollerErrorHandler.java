package NatParks.controller.error;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalcontrollerErrorHandler {
	private enum LogStatus {
		STACK_TRACE, MESSAGE_ONLY
	}
    @Data
	private class ExceptionMessage {
    	private String message;
    	private String statusReason;
    	private int statusCode;
    	private String timestamp;
    	private String uri;
    }
    
    public ExceptionMessage handleIllegalStateException(IllegalStateException ex, WebRequest webRequest) {
    	return buildExceptionMessage(ex, HttpStatus.BAD_REQUEST, webRequest, LogStatus.MESSAGE_ONLY);
    }
    
    private ExceptionMessage buildExceptionMessage(IllegalStateException ex, HttpStatus badRequest,
			WebRequest webRequest, LogStatus messageOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionMessage handleUnsupportedOperationException(UnsupportedOperationException ex, WebRequest webRequest) {
    	return buildExceptionMessage(ex, HttpStatus.METHOD_NOT_ALLOWED);
    	
    }


	private ExceptionMessage buildExceptionMessage(UnsupportedOperationException ex, HttpStatus methodNotAllowed) {
		// TODO Auto-generated method stub
		return null;
	}

	@ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ExceptionMessage handleNoSuchElementException(NoSuchElementException ex, WebRequest webRequest) {
    	return buildExceptionMessage(ex, HttpStatus.NOT_FOUND, webRequest, LogStatus.MESSAGE_ONLY);
    
    	
    	
    	
    	
    	
    }
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    public ExceptionMessage buildDuplicateKeyException(DuplicateKeyException ex, WebRequest webRequest) {
    	return buildExceptionMessage(ex, HttpStatus.CONFLICT, webRequest, LogStatus.MESSAGE_ONLY);
    }
    
    private ExceptionMessage buildExceptionMessage(DuplicateKeyException ex, HttpStatus conflict, WebRequest webRequest,
			LogStatus messageOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionMessage handleException(Exception ex, WebRequest webRequest) {
    	return buildExceptionMessage(ex, HttpStatus.INTERNAL_SERVER_ERROR, webRequest, LogStatus.STACK_TRACE);
    	
    }
    
    
    	
    
    private ExceptionMessage buildExceptionMessage(Exception ex, HttpStatus internalServerError, WebRequest webRequest,
			LogStatus stackTrace) {
		// TODO Auto-generated method stub
		return null;
	}

	private ExceptionMessage buildExceptionMessage(NoSuchElementException ex, HttpStatus status, WebRequest webRequest, LogStatus logStatus) {
    	String message = ex.toString();
    	String statusReason = status.getReasonPhrase();
    	int statusCode = status.value();
    	String uri = null;
    	String timestamp = ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME);
    	
    	if(webRequest instanceof ServletWebRequest swr) {
    		uri = swr.getRequest().getRequestURI();
    	}
    	
    	if(logStatus == LogStatus.MESSAGE_ONLY) {
    		log.error("Exception; {}", ex.toString());
    	}
    	else {
    		log.error("Exception: ", ex);
    	}
    	
    	ExceptionMessage excMsg = new ExceptionMessage();
    	
    	excMsg.setMessage(message);
    	excMsg.setStatusCode(statusCode);
    	excMsg.setStatusReason(statusReason);
    	excMsg.setTimestamp(timestamp);
    	excMsg.setUri(uri);
    	
    	
    	return excMsg;
    	
    }
    
}
