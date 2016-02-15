
package com.diary.services;

import com.diary.domain.Login;
import com.diary.restresponse.LoginResponse;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
public interface LoginService {

	/**
	 * @param login
	 * @return LoginResponse
	 */
	LoginResponse verifyWriterLogIn(final Login login);

}
