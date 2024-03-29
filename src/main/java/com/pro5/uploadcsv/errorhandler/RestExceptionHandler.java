package com.pro5.uploadcsv.errorhandler;

import javax.servlet.http.HttpServletRequest;

import com.pro5.uploadcsv.message.Error;
import com.pro5.uploadcsv.message.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    // Catch file size exceeded exception!
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@ExceptionHandler(MultipartException.class)
    @ResponseBody
    ResponseEntity<Response> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        Error err = new Error("0x123", ex.getMessage());
        Response res = new Response("error", err);
        
        return new ResponseEntity(res, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}