package com.demo.springboot.pojo;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiErrorResp {

	private int code;
	private String status;
	private String message;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd HH:mm:ss")
	private Date timeStamp;
	
	public ApiErrorResp() {
	}
	
	public ApiErrorResp(HttpStatus httpStatus) {
		this.code = httpStatus.value();
		this.status = httpStatus.name();
		timeStamp = new Date();
	}
	
	public ApiErrorResp(HttpStatus httpStatus, String message) {
		this(httpStatus);
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
