
package com.diary.service.implementation;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.diary.domain.Login;
import com.diary.repository.WritersRepository;
import com.diary.restresponse.LoginResponse;
import com.diary.services.LoginService;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@Service
public class LoginServiceImpl
	implements LoginService
{

	private final WritersRepository writersRepository;

	/**
	 * @param writersRepository
	 */
	@Inject
	public LoginServiceImpl(final WritersRepository writersRepository) {
		super();
		this.writersRepository = writersRepository;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see com.diary.services.LoginService#verifyWriterLogIn(com.diary.domain.Login)
	 */
	@Override
	public LoginResponse verifyWriterLogIn(Login login) {
		writersRepository.findByEmailAddress(login.getEmail());
		return null;
	}

}
