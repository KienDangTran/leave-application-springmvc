package com.giong.web.repository.mt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.giong.web.persistence.mt.MtUser;

public interface UserRepository extends JpaRepository<MtUser, String> {
	
	@Query("SELECT e FROM MtUser e WHERE e.username = :username")
	List<MtUser> loadUserByUsername(@Param("username") String username);
}
