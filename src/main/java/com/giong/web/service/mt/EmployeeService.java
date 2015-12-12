package com.giong.web.service.mt;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.repository.mt.EmployeeRepository;
import com.giong.web.service.BaseService;

@Transactional
@Service("employeeService")
@PreAuthorize("hasAuthority('VIEW_EMP')")
public class EmployeeService extends BaseService<MtEmployee, String, EmployeeRepository> {
	
	public List<MtEmployee> getAllEmployee() {
		return this.repository.findAll();
	}
	
	public MtEmployee findEmployeeyCode(String employeeCode) {
		return this.repository.findOne(employeeCode);
	}
	
	public void updateEmployee(MtEmployee employee) {
		this.repository.saveAndFlush(employee);
	}
	
}
