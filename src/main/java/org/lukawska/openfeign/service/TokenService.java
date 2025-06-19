package org.lukawska.openfeign.service;

import org.springframework.stereotype.Component;

@Component
public class TokenService {

	public String getValidToken(){
		return "TOKEN";
	}
}
