package com.giong.web.service.mt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.constant.Scheme;
import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.repository.mt.EmployeeRepository;
import com.giong.web.service.BaseService;

@Transactional
@Service("employeeService")
@PreAuthorize("hasAuthority('VIEW_EMP')")
public class EmployeeService extends BaseService<MtEmployee, String, EmployeeRepository> {
	
	@Autowired
	SchemeService schemeService;
	
	public List<MtEmployee> getAllEmployee() {
		return this.repository.findAll();
	}
	
	public MtEmployee findEmployeeyCode(String employeeCode) {
		return this.repository.findOne(employeeCode);
	}
	
	public void saveEmployee(MtEmployee employee) {
		this.repository.saveAndFlush(employee);
	}
	
	public MtEmployee createEmptyEmployee() {
		final MtEmployee newEmp = new MtEmployee();
		newEmp.setEmployeeCode(this.schemeService.generateNextId(Scheme.EMPLOYEE));
		return newEmp;
	}
	
	public void removeEmployee(MtEmployee currentEmployee) {
		this.repository.delete(currentEmployee);
	}
	
}
