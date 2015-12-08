package com.giong.web.repository.mt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giong.web.persistence.mt.MtRole;

public interface RoleRepository extends JpaRepository<MtRole, String> {

}
