
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.diary.domain.Writer;
import com.diary.restresponse.RegistrationResponse;
import com.diary.service.exception.AlreadyExistsException;
import com.diary.services.RegistrationService;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */

@Api(description = "Controller for registration process", name = "Registration Controller")
@RestController
public class RegistrationController {

	@Inject
	private RegistrationService registrationService;

	/**
	 * @param writer
	 * @return {@link ResponseEntity}
	 * @throws AlreadyExistsException
	 */
	@ApiMethod(path = "/register", verb = ApiVerb.POST, produces = {
		MediaType.APPLICATION_JSON_VALUE
	}, consumes = {
		MediaType.APPLICATION_JSON_VALUE
	}, responsestatuscode = "201 - Writer created", description = "This method registers a new writer into the application")
	@ApiErrors(apierrors = {
		@ApiError(code = "400", description = "Writer already exists"),
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
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = {
		MediaType.APPLICATION_JSON_VALUE
	}, consumes = {
		MediaType.APPLICATION_JSON_VALUE
	})
	public @ApiResponseObject ResponseEntity<RegistrationResponse> register(@ApiBodyObject @Validated @RequestBody Writer writer)
		throws AlreadyExistsException
	{
		RegistrationResponse registrationResponse = registrationService.registerWriter(writer);
		return new ResponseEntity<RegistrationResponse>(registrationResponse, HttpStatus.OK);
	}

}
