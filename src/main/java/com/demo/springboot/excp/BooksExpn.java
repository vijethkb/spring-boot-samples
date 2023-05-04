package com.demo.springboot.excp;

import com.demo.springboot.pojo.ApiRespCodes;

public class BooksExpn extends Exception {

	public BooksExpn(String msg) {
		super(msg);
	}

	public BooksExpn(ApiRespCodes apiRespCodes) {
		super(apiRespCodes.getMessage());
	}
	
}
