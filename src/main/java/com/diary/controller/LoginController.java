
package com.diary.controller;

import javax.inject.Inject;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiAuthBasic;
import org.jsondoc.core.annotation.ApiAuthBasicUser;
import org.jsondoc.core.annotation.ApiBodyObject;
import org.jsondoc.core.annotation.ApiError;
import org.jsondoc.core.annotation.ApiErrors;
import org.jsondoc.core.annotation.ApiHeader;
import org.jsondoc.core.annotation.ApiHeaders;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.jsondoc.core.pojo.ApiVerb;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diary.domain.Login;
import com.diary.restresponse.LoginResponse;
import com.diary.services.LoginService;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@RestController
@Api(description = "Controller for handling login request", name = "Login Controller")
public class LoginController {

	@Inject
	@Qualifier("loginServiceImpl")
	private LoginService loginService;

	/**
	 * @param loginService
	 */
	/*
	 * @Inject public LoginController(final LoginService loginService) {
	 * super(); this.loginService = loginService; }
	 */

	/**
	 * @param login
	 * @return {@link ResponseEntity}
	 */
	@ApiMethod(path = "/login", verb = ApiVerb.POST, produces = {
		MediaType.APPLICATION_JSON_VALUE
	}, consumes = {
		MediaType.APPLICATION_JSON_VALUE
	}, responsestatuscode = "201 - Writer logged in", description = "This method is called when a writer logs in into the application")
	@ApiErrors(apierrors = {
		@ApiError(code = "400", description = "Writer not found"),
	})
	@ApiAuthBasic(roles = {
		"ROLE_USER",
		"ROLE_ADMIN"
	}, testusers = {
		@ApiAuthBasicUser(username = "user", password = "pass")
	})
	@ApiHeaders(headers = {
		@ApiHeader(name = "email", description = "email of the writer")
	})
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = {
		MediaType.APPLICATION_JSON_VALUE
	}, consumes = {
		MediaType.APPLICATION_JSON_VALUE
	})
	public @ApiResponseObject ResponseEntity<LoginResponse> login(@ApiBodyObject @Validated @RequestBody Login login) {
		LoginResponse loginResponse = loginService.verifyWriterLogIn(login);
		return new ResponseEntity<LoginResponse>(loginResponse, HttpStatus.OK);
	}

}
