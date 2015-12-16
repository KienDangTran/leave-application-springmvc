package com.giong.web.repository.mt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.giong.web.persistence.mt.MtIdScheme;

public interface IdSchemeRepository extends JpaRepository<MtIdScheme, Integer> {
	
	@Query("SELECT e FROM MtIdScheme e WHERE e.schemeName = :schemeName")
	List<MtIdScheme> getSchemeByName(@Param("schemeName") String schemeName);
	
}
