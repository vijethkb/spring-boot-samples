package com.demo.springboot.service.auth;

import org.springframework.stereotype.Service;

import com.demo.springboot.enums.DBType;
import com.demo.springboot.pojo.AuthRequest;

@Service
public interface AuthService {
	
	public void doAuth(AuthRequest authReq);
	
	public default DBType getDBType() {
		return DBType.ORACLE;
	}
}
