
package com.diary.service.implementation;

import java.text.MessageFormat;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.diary.domain.Writer;
import com.diary.entity.Writers;
import com.diary.repository.WritersRepository;
import com.diary.restresponse.RegistrationResponse;
import com.diary.service.exception.AlreadyExistsException;
import com.diary.services.RegistrationService;
import com.diary.utility.MessageSourceUtility;
import com.diary.utility.Status;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@Service
public class RegistrationServiceImpl
	implements RegistrationService
{

	private final WritersRepository writersRepository;

	/**
	 * @param writersRepository
	 */
	@Inject
	public RegistrationServiceImpl(final WritersRepository writersRepository) {
		super();
		this.writersRepository = writersRepository;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see com.diary.services.RegistrationService#registerWriter(com.diary.domain.Writer)
	 */
	@Override
	public RegistrationResponse registerWriter(final Writer writer)
		throws AlreadyExistsException
	{
		Writers writers = null;
		String message = null;
		RegistrationResponse registerResponse = new RegistrationResponse();
		writers = writersRepository.findByEmailAddress(writer.getEmail());
		if (writers != null) {
			message = MessageSourceUtility.getMessage("diary.writer.regisration.emailalreadyexists.message");
			if (message != null) {
				message = MessageFormat.format(message, writer.getEmail());
				throw new AlreadyExistsException(message);
			}
		}
		writers = new Writers(writer.getFirstName(), writer.getMiddleName(), writer.getLastName(), writer.getEmail(), writer.getPassword());
		writers = writersRepository.save(writers);
		registerResponse.setStatus(Status.SUCCESS);
		message = MessageSourceUtility.getMessage("diary.writer.regisration.success.message");
		if (message != null) {
			message = MessageFormat.format(message, writer.getFirstName());
		}
		registerResponse.setMessage(message);
		return registerResponse;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see com.diary.services.RegistrationService#updateEmail(java.lang.String)
	 */
	@Override
	public RegistrationResponse updateEmail(final String email) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see com.diary.services.RegistrationService#updatePin(java.lang.String)
	 */
	@Override
	public RegistrationResponse updatePin(final String pin) {
		// TODO Auto-generated method stub
		return null;
	}

}
