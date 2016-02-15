
package com.diary.controller.junitMockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.diary.controller.RegistrationController;
import com.diary.domain.Writer;
import com.diary.restresponse.RegistrationResponse;
import com.diary.service.exception.AlreadyExistsException;
import com.diary.services.RegistrationService;
import com.diary.test.util.TestUtility;
import com.diary.utility.Status;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

	@Mock
	private RegistrationService registrationService;

	private RegistrationController registrationController;

	private Writer writer;

	/**
	 * @throws AlreadyExistsException
	 */
	@Test(expected = AlreadyExistsException.class)
	public void registerWriterTest_shouldReturnWithAlreadyExistsException()
		throws AlreadyExistsException
	{
		writer = new Writer();
		writer.setFirstName("Divyansh");
		writer.setLastName("Dixit");
		writer.setEmail("dixit@gmail.com");
		writer.setPasswword("abcd");

		when(registrationService.registerWriter(writer)).thenThrow(AlreadyExistsException.class);
		registrationController.register(writer);
	}

	/**
	 * @throws AlreadyExistsException
	 */
	@Test
	public void registerWriterTest_shouldReturnWithSuccessMessage()
		throws AlreadyExistsException
	{
		writer = new Writer();
		writer.setFirstName("Divyansh");
		writer.setLastName("Dixit");
		writer.setEmail("dixit@gmail.com");
		writer.setPasswword("abcd");

		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setStatus(Status.SUCCESS);
		registrationResponse.setMessage("Hi Divyansh, You are successfully registered with Dear-Diary.\nLogin using your registered email-id");

		when(registrationService.registerWriter(writer)).thenReturn(registrationResponse);

		ResponseEntity<RegistrationResponse> responseEntity = registrationController.register(writer);
		RegistrationResponse registrationResponse2 = responseEntity.getBody();

		assertEquals(registrationResponse.getMessage(), registrationResponse2.getMessage());
		assertEquals(registrationResponse.getStatus(), registrationResponse2.getStatus());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	/**
	 * @throws AlreadyExistsException
	 */
	@Test(expected = MethodArgumentNotValidException.class)
	public void registerWriterTest_shouldReturnWithValidationError()
		throws AlreadyExistsException
	{
		writer = new Writer();
		writer.setFirstName(TestUtility.createStringWithLength(70));
		writer.setLastName("Dixit");
		writer.setEmail("dixit@gmail.com");
		writer.setPasswword("abcd");

		when(registrationService.registerWriter(writer)).thenThrow(MethodArgumentNotValidException.class);
		registrationController.register(writer);
	}

	/**
	 *
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		registrationController = new RegistrationController();
		ReflectionTestUtils.setField(registrationController, "registrationService", registrationService);
	}

}
