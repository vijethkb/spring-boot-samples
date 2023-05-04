package com.demo.springboot.pojo;

import java.util.Arrays;
import java.util.Optional;

public enum ApiRespCodes {

	ID_EMPTY("Id is mandatory"),
	ID_INVALID_MAX_MIN("Id should be more than zero and less than 1000"),
	ID_ALREADY_EXISTS("Duplicate Id found, Id already exists"),
	ID_NOT_FOUND("Id is not available, unknown"),
	NAME_EMPTY("Name is mandatory"),
	NAME_INVALID_MAX_MIN("Name length should be more than 1 and less than 20"),
	NAME_ALREADY_EXISTS("Duplicate Name found, Name already exists"),
	NAME_NOT_FOUND("Name is not available, unknown"),
	SYS_UNAVAILABLE("Unknown error occured");
	
	private final String message;

	ApiRespCodes(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}
	
	public static Optional<ApiRespCodes> getRespCodesByMsg(String msg) {
        return Arrays.stream(ApiRespCodes.values())
            .filter(accStatus -> accStatus.message.equals(msg))
            .findFirst();
    }
	
}
