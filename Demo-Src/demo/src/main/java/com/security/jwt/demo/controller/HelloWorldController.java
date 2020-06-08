package com.security.jwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.jwt.demo.security.dto.res.JwtTokenResponse;
import com.security.jwt.demo.service.HelloService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/demo")
@Api(value = "Demo Controller", description = "This Controller contains APIs to test Different Roles , Super , Admin and User")
public class HelloWorldController {

	 @Autowired
	 private HelloService helloService;
	 
	@RequestMapping(value = "/public", method = RequestMethod.POST)
	@ApiOperation(value = "Test Public api without Role Authorization", response = JwtTokenResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Accessed Without login !!"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<?> sayHelloPublic() {

		return ResponseEntity.ok("Hello From Public !");
	}

	@PreAuthorize("hasRole('ROLE_SUPER')")
	@RequestMapping(value = "/SuperUser", method = RequestMethod.POST)
	@ApiOperation(value = "Test Super User Role Authorization", response = JwtTokenResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Authorized as Super User"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<?> sayHelloSuperUser() {

		return ResponseEntity.ok("Hello Super User !");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	@ApiOperation(value = "Test Admin Role Authorization", response = JwtTokenResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Authorized as Admin User"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<?> sayHelloAdmin() {

		return ResponseEntity.ok("Hello Admin !");
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ApiOperation(value = "Test User Role Authorization", response = JwtTokenResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Authorized as Regular User"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<?> sayHelloUser() {

		return ResponseEntity.ok("Hello User !");
	}

}
