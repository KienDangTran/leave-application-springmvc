package com.giong.lv.repository.mt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giong.lv.persistence.mt.MtUser;

public interface UserRepository extends JpaRepository<MtUser, String> {

}
