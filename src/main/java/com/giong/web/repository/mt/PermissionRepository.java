package com.giong.web.repository.mt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giong.web.persistence.mt.MtPermission;

public interface PermissionRepository extends JpaRepository<MtPermission, String> {

}
