
package com.diary.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.diary.entity.Writers;

/**
 * Description: Description goes here.
 *
 * @author <a href="mailto:dushyant.shukla@senecaglobal.com">Dushyant Shukla</a>
 * @version $Revision$ $Date$
 * @since 0.1
 */
public interface WritersRepository
	extends JpaRepository<Writers, String>
{

	/**
	 * @param email
	 * @return Writers
	 */
	@Transactional
	@Query("select w from Writers w where w.email = ?1")
	Writers findByEmailAddress(String email);

	/**
	 * @param newEmail
	 * @param oldEmail
	 * @return int
	 */
	@Transactional
	@Query("update Writers w set w.email = :newEmail where w.email = :oldEmail ")
	int updateEmail(@Param("newEmail") String newEmail, @Param("oldEmail") String oldEmail);

	/**
	 * @param email
	 * @param pin
	 * @return int
	 */
	@Transactional
	@Query("update Writers w set w.password = :pin where w.email = :email")
	int updatePin(@Param("email") String email, @Param("pin") String pin);

}
