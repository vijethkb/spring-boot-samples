package com.demo.springboot.service.auth;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.demo.springboot.enums.DBType;
import com.demo.springboot.pojo.AuthRequest;

@Component
public class PgAuthServiceImpl implements AuthService {

	Logger logger = LogManager.getLogger();
	
	@Override
	public void doAuth(AuthRequest authReq) {
		logger.info("Authenticating with PGDB");
	}

	@Override
	public DBType getDBType() {
		return DBType.PGDB;
	}
}
