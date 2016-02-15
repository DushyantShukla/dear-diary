/**
 *
 */

package com.diary.utility;

import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * @author dushyant.shukla
 */
public class MessageSourceUtility {

	private static MessageSource messageSource;

	/**
	 * @param key
	 * @return String
	 */
	public static String getMessage(String key) {
		return messageSource.getMessage(key, null, Locale.getDefault());
	}

	/**
	 * @param messageSource
	 */
	public static void setMessageSource(MessageSource messageSource) {
		MessageSourceUtility.messageSource = messageSource;
	}

}
