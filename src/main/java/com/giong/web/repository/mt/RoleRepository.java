package com.giong.web.repository.mt;

import com.giong.web.persistence.mt.MtRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<MtRole, String> {

}
