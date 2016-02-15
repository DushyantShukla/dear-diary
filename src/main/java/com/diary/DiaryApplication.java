
package com.diary;

import javax.annotation.PostConstruct;

import org.jsondoc.spring.boot.starter.EnableJSONDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;

import com.diary.utility.MessageSourceUtility;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@SpringBootApplication
@PropertySource("classpath:application.properties")
@EnableJSONDoc
@EnableAutoConfiguration(exclude = {
	SecurityAutoConfiguration.class
})
@ActiveProfiles("dev")
public class DiaryApplication
	extends SpringBootServletInitializer
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DiaryApplication.class, args);
	}

	@Autowired
	private MessageSource messageSource;

	@PostConstruct
	private void setMessageSource() {
		MessageSourceUtility.setMessageSource(messageSource);
	}

}
