
package com.diary.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.diary.restresponse.RestResponse;
import com.diary.service.exception.AlreadyExistsException;
import com.diary.utility.MessageSourceUtility;
import com.diary.utility.Status;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	/**
	 * @param exception
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<RestResponse> handleException(AlreadyExistsException exception) {
		RestResponse restResponse = new RestResponse();
		restResponse.setMessage(exception.getMessage());
		restResponse.setStatus(Status.ERROR);
		return new ResponseEntity<RestResponse>(restResponse, HttpStatus.CONFLICT);
	}

	/**
	 * @param ex
	 * @return ResponseEntity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<RestResponse>> validationHandler(MethodArgumentNotValidException ex) {
		List<RestResponse> restResponseList = new ArrayList<>();
		BindingResult bindingResult = ex.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			RestResponse restResponse = new RestResponse();
			restResponse.setStatus(Status.ERROR);
			restResponse.setMessage(MessageSourceUtility.getMessage(fieldError.getDefaultMessage()));
			restResponseList.add(restResponse);
		}
		return new ResponseEntity<List<RestResponse>>(restResponseList, HttpStatus.BAD_REQUEST);
	}
}
