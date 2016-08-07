package com.giong.web.repository.mt;

import com.giong.web.persistence.mt.MtEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<MtEmployee, String> {

}
