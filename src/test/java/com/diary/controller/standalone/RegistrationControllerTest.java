
package com.diary.controller.standalone;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.diary.controller.ControllerExceptionHandler;
import com.diary.controller.RegistrationController;
import com.diary.domain.Writer;
import com.diary.restresponse.RegistrationResponse;
import com.diary.service.exception.AlreadyExistsException;
import com.diary.services.RegistrationService;
import com.diary.test.util.TestUtility;
import com.diary.utility.MessageSourceUtility;
import com.diary.utility.Status;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationControllerTest {

	@Mock
	private RegistrationService registrationService;

	@Mock
	private MessageSource messageSource;

	MockMvc mockMvc;

	Writer writer;

	/**
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void registerWriterTest_shouldReturnWithAlreadyExistingEmailException()
		throws Exception
	{
		writer = new Writer();
		writer.setFirstName("Divyansh");
		writer.setLastName("Dixit");
		writer.setEmail("dixit@gmail.com");
		writer.setPasswword("abcd");

		byte[] jsonRequestContent = TestUtility.convertObjectToJsonBytes(writer);

		when(MessageSourceUtility.getMessage("diary.writer.regisration.emailalreadyexists.message")).thenReturn("Email-id dixit@gmail.com already exists");
		when(registrationService.registerWriter(isA(Writer.class))).thenThrow(new AlreadyExistsException("Email-id dixit@gmail.com already exists"));

		MockHttpServletRequestBuilder postRequest = post("/register").contentType(TestUtility.APPLICATION_JSON_UTF8).content(jsonRequestContent);
		ResultActions results = mockMvc.perform(postRequest).andDo(MockMvcResultHandlers.print());

		results.andExpect(status().isConflict());
		results.andExpect(jsonPath("$.status").value("ERROR"));
		results.andExpect(jsonPath("$.message").value("Email-id dixit@gmail.com already exists"));
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void registerWriterTest_shouldReturnWithSuccessMessage()
		throws Exception
	{
		writer = new Writer();
		writer.setFirstName("Divyansh");
		writer.setLastName("Dixit");
		writer.setEmail("dixit@gmail.com");
		writer.setPasswword("abcd");

		byte[] jsonRequestContent = TestUtility.convertObjectToJsonBytes(writer);

		RegistrationResponse registrationResponse = new RegistrationResponse();
		registrationResponse.setStatus(Status.SUCCESS);
		registrationResponse.setMessage("Hi Divyansh, You are successfully registered with Dear-Diary.\nLogin using your registered email-id");

		when(registrationService.registerWriter(isA(Writer.class))).thenReturn(registrationResponse);

		MockHttpServletRequestBuilder postRequest = post("/register").contentType(TestUtility.APPLICATION_JSON_UTF8).content(jsonRequestContent);

		ResultActions results = mockMvc.perform(postRequest).andDo(MockMvcResultHandlers.print());

		results.andExpect(status().isOk());
		results.andExpect(jsonPath("$.status").value(registrationResponse.getStatus().toString()));
		results.andExpect(jsonPath("$.message").value(registrationResponse.getMessage()));
	}

	/**
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void registerWriterTest_shouldReturnWithValidationErrorMessage()
		throws Exception
	{
		writer = new Writer();
		writer.setFirstName(TestUtility.createStringWithLength(70));
		writer.setLastName("Dixit");
		writer.setEmail("dixit@gmail.com");
		writer.setPasswword("abcd");

		byte[] jsonRequestContent = TestUtility.convertObjectToJsonBytes(writer);

		when(registrationService.registerWriter(isA(Writer.class))).thenThrow(MethodArgumentNotValidException.class);
		when(MessageSourceUtility.getMessage("error.firstname.size")).thenReturn("The firstname should be limited to 64 characters");

		MockHttpServletRequestBuilder postRequest = post("/register").contentType(TestUtility.APPLICATION_JSON_UTF8).content(jsonRequestContent);
		ResultActions results = mockMvc.perform(postRequest).andDo(MockMvcResultHandlers.print());

		results.andExpect(status().isBadRequest());
		results.andExpect(jsonPath("$.[*].status").value("ERROR"));
		results.andExpect(jsonPath("$.[*].message").value("The firstname should be limited to 64 characters"));
	}

	/**
	 *
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		RegistrationController registrationController = new RegistrationController();
		ReflectionTestUtils.setField(registrationController, "registrationService", registrationService);
		mockMvc = MockMvcBuilders.standaloneSetup(registrationController).setControllerAdvice(new ControllerExceptionHandler()).build();
		MessageSourceUtility.setMessageSource(messageSource);
	}

}
