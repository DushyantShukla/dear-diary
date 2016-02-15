
package com.diary.restresponse;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import com.diary.utility.Status;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@ApiObject
public class RegistrationResponse {

	@ApiObjectField(description = "response status", required = true)
	private Status status;

	@ApiObjectField(description = "response message", required = true)
	private String message;

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param message1
	 */
	public void setMessage(final String message1) {
		message = message1;
	}

	/**
	 * @param status
	 */
	public void setStatus(final Status status) {
		this.status = status;
	}
}
