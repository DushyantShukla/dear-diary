
package com.diary;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@Configuration
@EnableWebMvc
public class MVCConfiguration
	extends WebMvcConfigurerAdapter
{

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
		"classpath:/META-INF/resources/",
		"classpath:/resources/",
		"classpath:/static/",
		"classpath:/public/"
	};

	/**
	 * {@inheritDoc}
	 *
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addViewControllers(org.springframework.web.servlet.config.annotation.ViewControllerRegistry)
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("DearDiaryWithForms");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}

}
