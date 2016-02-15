/**
 *
 */

package com.diary.services;

import com.diary.domain.Writer;
import com.diary.restresponse.RegistrationResponse;
import com.diary.service.exception.AlreadyExistsException;

/**
 * @author dushyant.shukla
 */
public interface RegistrationService {

	/**
	 * @param writer
	 * @return {@link RegistrationResponse}
	 * @throws AlreadyExistsException
	 */
	RegistrationResponse registerWriter(final Writer writer)
		throws AlreadyExistsException;

	/**
	 * @param email
	 * @return {@link RegistrationResponse}
	 */
	RegistrationResponse updateEmail(final String email);

	/**
	 * @param pin
	 * @return {@link RegistrationResponse}
	 */
	RegistrationResponse updatePin(final String pin);

}
