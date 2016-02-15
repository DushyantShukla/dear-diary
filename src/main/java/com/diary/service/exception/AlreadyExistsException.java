
package com.diary.service.exception;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
@SuppressWarnings("serial")
public class AlreadyExistsException
	extends Exception
{

	/**
	 * @param message
	 */
	public AlreadyExistsException(final String message) {
		super(message);
	}
}
