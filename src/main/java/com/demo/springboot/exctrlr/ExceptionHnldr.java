package com.demo.springboot.exctrlr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.demo.springboot.excp.BooksExpn;
import com.demo.springboot.pojo.ApiErrorResp;

@RestControllerAdvice
public class ExceptionHnldr {

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE)
	public ApiErrorResp handleException(Exception ex) {
		ApiErrorResp apiError = new ApiErrorResp(HttpStatus.SERVICE_UNAVAILABLE);
		apiError.setMessage(ex.getMessage());
		return apiError;
	}

	@ExceptionHandler(value = BooksExpn.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiErrorResp handleBookNotFound(BooksExpn ex) {
		ApiErrorResp apiError = new ApiErrorResp(HttpStatus.BAD_REQUEST,ex.getMessage());
		return apiError;
	}

}
