package com.demo.springboot.ctrlr;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springboot.pojo.Identity;

@RestController
public class VersionCtrlr {
	
	Logger logger = LogManager.getLogger();
	
	@GetMapping
	public Identity getDefault() {
		logger.info("assign default mapping -- getDefault()");
		return new Identity(1,"Default Identity");
	}

	@GetMapping("v1/identity")
	public Identity getV1PathParam(@RequestHeader HttpHeaders headers) {
		return new Identity(1,"v1");
	}
	
	@GetMapping("v2/identity")
	public Identity getV2PathParam() {
		return new Identity(2,"v2");
	}

	@GetMapping(value="/identity", headers = "X-API-VERSION=1")
	public Identity getV1Header() {
		return new Identity(1,"X-API-VERSION=1");
	}
	
	@GetMapping(value="/identity", headers = "X-API-VERSION=2")
	public Identity getV2Header() {
		return new Identity(2,"X-API-VERSION=2");
	}
	
	@GetMapping("/headers")
	public Identity getAllHeaders(@RequestHeader HttpHeaders headers) {
		StringBuffer headerInfo = new StringBuffer();
		for(String header : headers.keySet()){
			headerInfo.append(header+"="+headers.get(header)+",");
		}
		return new Identity(1,headerInfo.toString());
	}

}
