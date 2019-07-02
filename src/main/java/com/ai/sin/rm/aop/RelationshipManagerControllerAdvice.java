package com.ai.sin.rm.aop;

import com.ai.sin.rm.controller.RelationshipManagerController;
import com.ai.sin.rm.exception.RelationshipManagerException;
import com.ai.sin.rm.model.GenericDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * exception handler
 */
@ControllerAdvice(basePackageClasses = RelationshipManagerController.class)
public class RelationshipManagerControllerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RelationshipManagerControllerAdvice.class);

    private static final String BUSINESS_EXCEPTION = "BUSINESS_EXCEPTION";

    private static final String TECHNICAL_EXCEPTION = "TECHNICAL_EXCEPTION";

    /**
     * Know exception
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        String errorType = "";
        String errorCode = "";
        if (ex instanceof RelationshipManagerException) {
            errorType = BUSINESS_EXCEPTION;
            errorCode = "500";
            LOG.info("Business exception caused.", ex);
        } else {
            errorType = TECHNICAL_EXCEPTION;
            errorCode = status.value() + "";
            LOG.error("Technical exception caused.", ex);
        }
        GenericDto sinCurrencyDto = new GenericDto();
        GenericDto.Header header = new GenericDto.Header();
        header.setAuthToken("");
        header.setSuccess(false);
        sinCurrencyDto.setHeader(header);
        GenericDto.Error error = new GenericDto.Error();
        error.setErrorCode(errorCode);
        error.setErrorMessage(ex.getMessage());
        error.setErrorType(errorType);
        sinCurrencyDto.setError(error);
        return new ResponseEntity<>(sinCurrencyDto, status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
