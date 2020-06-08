package com.security.jwt.demo.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.jwt.demo.enums.RoleType;
import com.security.jwt.demo.exception.custom.EmailAlreadyExistException;
import com.security.jwt.demo.req.RegistrationForm;
import com.security.jwt.demo.service.RegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/user/registration")
@Api(value = "Registration Controller", description = "This Controller contains APIs for Register Admin and regular user ")
public class RegistrationController {

	@Value("${jwt.http.request.header}")
	private String tokenHeader;

	@Value("${jwt.get.token.uri}")
	private String authenticationPath;

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/regular", method = RequestMethod.POST)
	@ApiOperation(value = "Register new User as A Reqular user")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully Regular user Added and waiting for confirmation by email"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationForm registrationForm)
			throws EmailAlreadyExistException, URISyntaxException {

		registrationService.saveUser(registrationForm, RoleType.ROLE_USER);
		return ResponseEntity.created(new URI(authenticationPath)).build();
	}

	@RequestMapping(value = "/admin", method = RequestMethod.POST)
	@ApiOperation(value = "Register new User as An Admin user")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Successfully Admin user Added and waiting for confirmation by email"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public ResponseEntity<?> registerAdmin(@RequestBody @Valid RegistrationForm registrationForm)
			throws EmailAlreadyExistException, URISyntaxException {

		registrationService.saveUser(registrationForm, RoleType.ROLE_ADMIN);
		return ResponseEntity.created(new URI(authenticationPath)).build();
	}

}