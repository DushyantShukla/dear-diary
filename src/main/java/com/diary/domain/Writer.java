
package com.diary.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@ApiObject
@JsonIgnoreProperties({
	"middleName"
})
public final class Writer {

	@Valid
	@NotNull(message = "error.fisrtname.notnull")
	@Size(min = 1, max = 64, message = "error.firstname.size")
	@ApiObjectField(description = "writer's first name", required = true)
	private String firstName;

	@Valid
	@NotNull(message = "error.lastname.notnull")
	@Size(min = 1, max = 64, message = "error.lastname.size")
	@ApiObjectField(description = "writer's last name", required = true)
	private String lastName;

	// @JsonIgnore
	@Valid
	@Size(min = 0, max = 64, message = "error.middlename.size")
	@ApiObjectField(description = "writer's middle name", required = false)
	private String middleName;

	@Valid
	@NotNull(message = "error.email.notnull")
	@Size(min = 1, max = 512, message = "error.email.size")
	@ApiObjectField(description = "writer's email", required = true)
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
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return middleName
	 */
	// @JsonIgnore
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param email1
	 */
	public void setEmail(String email1) {
		email = email1;
	}

	/**
	 * @param firstName1
	 */
	public void setFirstName(String firstName1) {
		firstName = firstName1;
	}

	/**
	 * @param lastName1
	 */
	public void setLastName(String lastName1) {
		lastName = lastName1;
	}

	/**
	 * @param middleName1
	 */
	// @JsonProperty
	public void setMiddleName(String middleName1) {
		middleName = middleName1;
	}

	/**
	 * @param password1
	 */
	public void setPasswword(String password1) {
		password = password1;
	}

}
