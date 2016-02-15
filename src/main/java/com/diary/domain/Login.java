/**
 *
 */

package com.diary.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

/**
 * @author dushyant.shukla
 */
@ApiObject
public class Login {

	@Valid
	@NotNull(message = "error.email.notnull")
	@Size(min = 1, max = 512, message = "error.email.size")
	@ApiObjectField(description = "writer's email-id (login id)", required = true)
	private String email;

	@Valid
	@NotNull(message = "error.password.notnull")
	@Size(min = 1, max = 512, message = "error.password.size")
	@ApiObjectField(description = "writer's account password", required = true)
	private String password;

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
