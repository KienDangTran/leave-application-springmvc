package com.giong.web.repository.mt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giong.web.persistence.mt.MtUser;

public interface UserRepository extends JpaRepository<MtUser, String> {

}
