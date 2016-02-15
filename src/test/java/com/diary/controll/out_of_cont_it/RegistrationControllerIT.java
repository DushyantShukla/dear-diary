/**
 *
 */

package com.diary.controll.out_of_cont_it;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.diary.DiaryApplication;
import com.diary.domain.Writer;
import com.diary.test.util.TestUtility;
import com.diary.utility.Status;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:xxx@xxx">xxx</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = DiaryApplication.class)
@ActiveProfiles("test")
public class RegistrationControllerIT {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private Writer writer;

	/**
	 * @throws Exception
	 */
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:already_exists_exception.sql")
	@Test
	public void registerWriterIT_shouldResultWithAlreadyExistsException()
		throws Exception
	{
		writer = new Writer();
		writer.setFirstName("Drishti");
		writer.setLastName("Daman");
		writer.setEmail("drishti@gmail.com");
		writer.setPasswword("abcd");

		byte[] jsonRequestContent = TestUtility.convertObjectToJsonBytes(writer);
		MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/register").contentType(TestUtility.APPLICATION_JSON_UTF8).content(jsonRequestContent);
		ResultActions result = mockMvc.perform(postRequest).andDo(MockMvcResultHandlers.print());

		result.andExpect(MockMvcResultMatchers.status().isConflict());
		result.andExpect(jsonPath("$.status").value(Status.ERROR.toString()));
		result.andExpect(jsonPath("$.message").value("Email-id drishti@gmail.com already exists"));

	}

	/**
	 * @throws Exception
	 */
	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:successful_registration.sql")
	@Test
	public void registerWriterIT_shouldReturnWithSuccessMessage()
		throws Exception
	{
		writer = new Writer();
		writer.setFirstName("Drishti");
		writer.setMiddleName("Rao");
		writer.setLastName("Daman");
		writer.setEmail("drishtidaman@gmail.com");
		writer.setPasswword("abcd");

		byte[] jsonRequestContent = TestUtility.convertObjectToJsonBytes(writer);

		MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/register").contentType(TestUtility.APPLICATION_JSON_UTF8).content(jsonRequestContent);

		ResultActions result = mockMvc.perform(postRequest).andDo(MockMvcResultHandlers.print());

		result.andExpect(MockMvcResultMatchers.status().isOk());
		result.andExpect(jsonPath("$.status").value(Status.SUCCESS.toString()));
		result.andExpect(jsonPath("$.message").value("Hi Drishti, You are successfully registered with Dear-Diary.\nLogin using your registered email-id"));

	}

	/**
	 * @throws Exception
	 */
	@Test
	public void registerWriterIT_shouldReturnWithValidationError()
		throws Exception
	{
		writer = new Writer();
		writer.setFirstName(TestUtility.createStringWithLength(70));
		writer.setLastName("Daman");
		writer.setEmail("drishti1@gmail.com");
		writer.setPasswword("abcd");

		byte[] jsonRequestContent = TestUtility.convertObjectToJsonBytes(writer);

		MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/register").contentType(TestUtility.APPLICATION_JSON_UTF8).content(jsonRequestContent);

		ResultActions result = mockMvc.perform(postRequest).andDo(MockMvcResultHandlers.print());

		result.andExpect(MockMvcResultMatchers.status().isBadRequest());
		result.andExpect(jsonPath("$.[0].status").value(Status.ERROR.toString()));
		result.andExpect(jsonPath("$.[0].message").value("The firstname should be limited to 64 characters"));

	}

	/**
	 *
	 */
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

}
