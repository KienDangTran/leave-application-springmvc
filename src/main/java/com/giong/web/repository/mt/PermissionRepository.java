package com.giong.web.repository.mt;

import com.giong.web.persistence.mt.MtPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<MtPermission, String> {

}
