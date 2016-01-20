package com.giong.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.Filter;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.giong.config.TestContext;
import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.service.mt.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class })
@WebAppConfiguration
public class EmployeeControllerTest {
	
	private MockMvc mockMvc;
	
	private Validator validator;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private EmployeeService employeeServiceMock;
	
	@Before
	public void setup() {
		Mockito.reset(this.employeeServiceMock);
		// @formatter:off
		this.mockMvc = MockMvcBuilders
							.webAppContextSetup(this.context)
							.defaultRequest(MockMvcRequestBuilders.get("/").with(SecurityMockMvcRequestPostProcessors.testSecurityContext()))
							.alwaysDo(MockMvcResultHandlers.print())
							.addFilters(this.springSecurityFilterChain)
							.build();
		// @formatter:on
		
		final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();
	}
	
	@Test
	@WithMockUser(authorities = { "VIEW_EMP", "EXE_EMP" })
	public void testGetAllEmployees() throws Exception {
		final java.util.List<MtEmployee> allEmployees = new ArrayList<MtEmployee>();
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		MtEmployee e1 = new MtEmployee();
		e1.setEmployeeCode("EMP_TEST_001");
		e1.setEmployeeName("First Employee Test");
		e1.setSex("M");
		e1.setDateOfBirth(sdf.parse("1990-09-29"));
		e1.setPhoneNo("0944346576");
		e1.setEmail("email1@example.com");
		allEmployees.add(e1);
		Set<ConstraintViolation<MtEmployee>> violations = this.validator.validate(e1);
		Assert.assertTrue(violations.isEmpty());
		
		e1 = new MtEmployee();
		e1.setEmployeeCode("EMP_TEST_002");
		e1.setEmployeeName("Second Employee Test");
		e1.setSex("F");
		e1.setDateOfBirth(sdf.parse("1990-09-29"));
		allEmployees.add(e1);
		violations = this.validator.validate(e1);
		Assert.assertTrue(violations.isEmpty());
		
		Mockito.when(this.employeeServiceMock.getAllEmployee()).thenReturn(allEmployees);
		
		// @formatter:off
		this.mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name(EmployeeController.EMPLOYEE_VIEW_NAME))
					.andExpect(MockMvcResultMatchers.model().attribute("allEmployees", allEmployees))
					;
		
		// @formatter:off
	}
}
