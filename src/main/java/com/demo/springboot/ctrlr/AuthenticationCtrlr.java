package com.demo.springboot.ctrlr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.enums.DBType;
import com.demo.springboot.excp.AuthExpn;
import com.demo.springboot.pojo.AuthRequest;
import com.demo.springboot.pojo.AuthResponse;
import com.demo.springboot.service.auth.AuthService;
import com.demo.springboot.val.LoginReqValidator;

@RestController
public class AuthenticationCtrlr {

	Logger logger = LogManager.getLogger();

	Map<String,DBType> oraMap = new HashMap<String,DBType>();
	private void preloadUserDBMap() {
		oraMap.put("OraUser1",DBType.ORACLE);
		oraMap.put("OraUser2",DBType.ORACLE);
		oraMap.put("pgUser1",DBType.PGDB);
		oraMap.put("pgUser2",DBType.PGDB);
	}

	@Autowired
	List<AuthService> authService;

	@PostMapping("/doAuth")
	public AuthResponse doAuth(@RequestBody AuthRequest authReq) throws AuthExpn {
		preloadUserDBMap();
		AuthService authServiceImpl = null;
		AuthResponse authResponse = new AuthResponse();
		DBType db = DBType.ORACLE;
		if(!authReq.getUserName().isBlank() && oraMap.containsKey(authReq.getUserName())) {
			db = oraMap.get(authReq.getUserName());
		}
		for(AuthService authServ : authService) {
			if(authServ.getDBType() == db){
				authServiceImpl = authServ;
				break;
			}
		}
		// do parameter validations - Start
		LoginReqValidator.validateAuthParam(logger, authReq);
		// do parameter validations - End
		authServiceImpl.doAuth(authReq);
		authResponse.setUniqReqId(UUID.randomUUID().toString());
		return authResponse;
	}
	
}
