package com.security.jwt.demo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

	public String sayHelloFromService() {
		return "Hello From Service";
	}
}
