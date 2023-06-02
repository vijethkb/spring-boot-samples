package com.demo.springboot.val;

import org.apache.logging.log4j.Logger;

import com.demo.springboot.excp.AuthExpn;
import com.demo.springboot.pojo.AuthRequest;

public class LoginReqValidator {

	/**
	 * do the parameter validation here and throw AuthExpn as required
	 */
	public static boolean validateAuthParam(Logger logger, AuthRequest authReq) throws AuthExpn {
		logger.info("Validating authParams - username and password");
		boolean validRequest = false;
		validRequest = true; // keeping it true for now
		return validRequest;
	}

}
