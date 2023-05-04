package com.demo.springboot.val;

import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import com.demo.springboot.excp.BooksExpn;
import com.demo.springboot.pojo.ApiRespCodes;

public class BookValidator {

	public static boolean isBookDataValid(Logger logger, Integer id, String name) throws BooksExpn {
		boolean validBook = false;
		if(id == null ) {
			logger.error("id is mandatory");
			throw new BooksExpn(ApiRespCodes.ID_EMPTY);
		}
		if(id < 0 ) {
			logger.error("negative id value [ "+id+"]");
			throw new BooksExpn(ApiRespCodes.ID_INVALID_MAX_MIN);
		}
		if(StringUtils.isEmpty(name)) {
			logger.error("name is mandatory");
			throw new BooksExpn(ApiRespCodes.NAME_EMPTY);
		}
		validBook = true;
		return validBook;
	}
	
}
