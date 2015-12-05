package com.giong.web.service.mt;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.repository.mt.EmployeeRepository;
import com.giong.web.service.BaseService;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeService extends BaseService<MtEmployee, String, EmployeeRepository> {
	
	public List<MtEmployee> getAllEmployee() {
		return this.repository.findAll();
	}
	
	public MtEmployee findEmployeeyCode(String employeeCode) {
		return this.repository.findOne(employeeCode);
	}
	
}
