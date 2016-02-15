
package com.diary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */

@Entity
@Table(name = "Writers")
public class Writers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "writerid", nullable = false)
	private int writerId;

	@Size(min = 1, max = 64)
	@Column(name = "firstname", nullable = false)
	private String firstName;

	@Size(min = 1, max = 64)
	@Column(name = "middlename", nullable = true)
	private String middleName;

	@Size(min = 1, max = 64)
	@Column(name = "lastname", nullable = false)
	private String lastName;

	@Size(min = 1, max = 512)
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@Size(min = 1, max = 512)
	@Column(name = "pin", nullable = false)
	private String password;

	/**
     *
     */
	public Writers() {
	}

	/**
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param email
	 * @param password
	 */
	public Writers(final String firstName, final String middleName, final String lastName, final String email, final String password) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return writerId
	 */
	public int getWriterId() {
		return writerId;
	}

	/**
	 * @param email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}

	/**
	 * @param password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * @param writerId
	 */
	public void setWriterId(final int writerId) {
		this.writerId = writerId;
	}

}
