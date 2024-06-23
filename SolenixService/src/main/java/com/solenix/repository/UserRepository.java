/**
 * 
 */
package com.solenix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solenix.model.User;

/**
 * @author solenix
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
}
