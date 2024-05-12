package com.workintech.s17d3.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Slf4j
@ControllerAdvice

public class GlobalExceptionHandler {
    @ExceptionHandler

    public ResponseEntity<ZooErrorResponse> handleApiException(ZooException zooException){
        log.error("ZooException occured! Exception details:"+zooException.getLocalizedMessage());
        ZooErrorResponse zooErrorResponse= new ZooErrorResponse(zooException.getHttpStatus().value(),zooException.getLocalizedMessage(),System.currentTimeMillis());
     return new ResponseEntity<>(zooErrorResponse,zooException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleAllException(Exception exception){
        log.error("Exception occured! Exception details :" + exception.getLocalizedMessage());
        ZooErrorResponse errorResponse =new ZooErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),exception.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
