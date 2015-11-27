package com.giong.web.service.mt.user.mt.employee;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.giong.web.repository.mt.EmployeeRepository;

@Service("employeeService")
@Transactional(readOnly = true)
public class EmployeeService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private EmployeeRepository employeeRepository;
}
