package com.demo.springboot.excp;

import com.demo.springboot.pojo.ApiRespCodes;

public class AuthExpn extends Exception {

	public AuthExpn(String msg) {
		super(msg);
	}

	public AuthExpn(ApiRespCodes apiRespCodes) {
		super(apiRespCodes.getMessage());
	}
	
}
