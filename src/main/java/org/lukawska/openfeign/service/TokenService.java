package org.lukawska.openfeign.service;

import org.springframework.stereotype.Service;

@Service
public class TokenService {

	public String getValidToken() {
		return "test token";
	}
}
