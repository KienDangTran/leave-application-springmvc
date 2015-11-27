package com.giong.web.repository.mt;

import org.springframework.data.jpa.repository.JpaRepository;

import com.giong.web.persistence.mt.MtEmployee;

public interface EmployeeRepository extends JpaRepository<MtEmployee, String> {

}
