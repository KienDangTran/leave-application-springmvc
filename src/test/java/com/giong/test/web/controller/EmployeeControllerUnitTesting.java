package com.giong.test.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.Filter;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.giong.constant.RequestURL;
import com.giong.constant.View;
import com.giong.exception.NotFoundException;
import com.giong.test.config.TestContext;
import com.giong.test.util.TestUtils;
import com.giong.web.persistence.JsonResponse;
import com.giong.web.persistence.mt.MtEmployee;
import com.giong.web.service.MessageService;
import com.giong.web.service.mt.EmployeeService;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestContext.class })
@WebAppConfiguration
@WithMockUser(authorities = { "VIEW_EMP", "EXE_EMP" })
public class EmployeeControllerUnitTesting {
	
	private MockMvc mockMvc;
	
	private Validator validator;
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Autowired
	private EmployeeService employeeServiceMock;
	
	@Autowired
	private MessageService messageServiceMock;
	
	@Before
	public void setup() {
		// @formatter:off
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
									.defaultRequest(MockMvcRequestBuilders.get(RequestURL.HOME).with(SecurityMockMvcRequestPostProcessors.testSecurityContext()))
									.alwaysDo(MockMvcResultHandlers.print())
									.addFilters(this.springSecurityFilterChain)
									.build();
		// @formatter:on
		final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		this.validator = vf.getValidator();
	}
	
	@Test
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
		this.mockMvc.perform(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_SUMMARY))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name(View.EMPLOYEE_SUMMARY.getViewName()))
					.andExpect(MockMvcResultMatchers.model().attribute("allEmployees", allEmployees))
					.andExpect(MockMvcResultMatchers.model().attribute("allEmployees", Matchers.hasSize(2)))
					.andExpect(MockMvcResultMatchers.model().attribute("allEmployees", 
						Matchers.hasItem(Matchers.allOf(Matchers.hasProperty("employeeCode", Matchers.is("EMP_TEST_001")),
														Matchers.hasProperty("employeeName", Matchers.is("First Employee Test")), Matchers.hasProperty("sex", Matchers.is("M")),
														Matchers.hasProperty("dateOfBirth", Matchers.is(sdf.parse("1990-09-29"))), Matchers.hasProperty("phoneNo", Matchers.is("0944346576")),
														Matchers.hasProperty("email", Matchers.is("email1@example.com"))))))
					.andExpect(MockMvcResultMatchers.model().attribute("allEmployees",
						Matchers.hasItem(Matchers.allOf(Matchers.hasProperty("employeeCode", Matchers.is("EMP_TEST_002")),
														Matchers.hasProperty("employeeName", Matchers.is("Second Employee Test")), Matchers.hasProperty("sex", Matchers.is("F")),
														Matchers.hasProperty("dateOfBirth", Matchers.is(sdf.parse("1990-09-29"))), Matchers.hasProperty("phoneNo", Matchers.isEmptyOrNullString()),
														Matchers.hasProperty("email", Matchers.isEmptyOrNullString())))));
		// @formatter:on
	}
	
	@Test
	public void testViewEmployeeDetails_Success() throws Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		final MtEmployee e1 = new MtEmployee();
		e1.setEmployeeCode("EMP_TEST_001");
		e1.setEmployeeName("First Employee Test");
		e1.setSex("M");
		e1.setDateOfBirth(sdf.parse("1990-09-29"));
		e1.setPhoneNo("0944346576");
		e1.setEmail("email1@example.com");
		final Set<ConstraintViolation<MtEmployee>> violations = this.validator.validate(e1);
		Assert.assertTrue(violations.isEmpty());
		
		Mockito.when(this.employeeServiceMock.findEmployeeyByCode("EMP_TEST_001")).thenReturn(e1);
		// @formatter:off
		this.mockMvc.perform(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_DETAIL, "EMP_TEST_001"))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name(View.EMPLOYEE_DETAIL.getViewName()))
					.andExpect(MockMvcResultMatchers.model().attribute("currentEmployee", e1))
					.andExpect(MockMvcResultMatchers.model().attribute("readonly", true));
		// @formatter:on
	}
	
	@Test
	public void testViewEmployeeDetails_NotFound() throws Exception {
		Mockito.when(this.messageServiceMock.getMessages("err.employee_is_not_found", "EMP_TEST_002")).thenReturn("Employee \"EMP_TEST_002\" is not found.");
		// @formatter:off
		this.mockMvc.perform(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_DETAIL, "EMP_TEST_002"))
					.andExpect(MockMvcResultMatchers.status().isNotFound())
					.andExpect(MockMvcResultMatchers.view().name(View.ERROR_404.getViewName()))
					.andExpect(MockMvcResultMatchers.model().attribute("exception", Matchers.isA(NotFoundException.class)))
					.andExpect(MockMvcResultMatchers.model().attribute("exception", Matchers.anything(this.messageServiceMock.getMessages("err.employee_is_not_found", "EMP_TEST_002"))));
	}
	
	@Test
	public void testAddEmployee() throws Exception {
		final MtEmployee e2 = new MtEmployee();
		e2.setEmployeeCode("EMP_TEST_002");
		Mockito.when(this.employeeServiceMock.createEmptyEmployee()).thenReturn(e2);
		
		// @formatter:off
		this.mockMvc.perform(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_ADD))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name(View.EMPLOYEE_DETAIL.getViewName()))
				.andExpect(MockMvcResultMatchers.model().attribute("currentEmployee", e2))
				;
		
		// @formatter:on
	}
	
	@Test
	public void testSaveOrUpdateEmployee_Success() throws Exception {
		
		Mockito.when(this.messageServiceMock.getMessages("msg.all_info_have_been_saved_successfully")).thenReturn("All Information have been saved successfully");
		Mockito.when(this.messageServiceMock.getMessages("validator.employee_name_is_required")).thenReturn("Employee Name is required!");
		Mockito.when(this.messageServiceMock.getMessages("validator.dob_is_required")).thenReturn("Date of birth is required!");
		Mockito.when(this.messageServiceMock.getMessages("validator.email_is_not_well_formed")).thenReturn("Email is not well formed! (eg. example@domain.com)");
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		final MtEmployee e1 = new MtEmployee();
		e1.setEmployeeCode("EMP_TEST_001");
		e1.setEmployeeName("First Employee Test");
		e1.setSex("M");
		e1.setDateOfBirth(sdf.parse("1990-09-29"));
		e1.setPhoneNo("0944346576");
		e1.setEmail("email1@example.com");
		// @formatter:off
		this.mockMvc.perform(MockMvcRequestBuilders.post(RequestURL.EMPLOYEE_DETAIL, "EMP_TEST_001")
													.contentType(TestUtils.APPLICATION_JSON_UTF8)
													.content(TestUtils.convertObjectToJsonBytes(e1)).with(SecurityMockMvcRequestPostProcessors.csrf()))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(TestUtils.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(JsonResponse.RESPONSE_STATUS_SUCCESS)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.result", Matchers.is(this.messageServiceMock.getMessages("msg.all_info_have_been_saved_successfully"))));
		// @formatter:on
	}
	
	@Test
	public void testSaveOrUpdateEmployee_ValidateFailed() throws Exception {
		final MtEmployee e2 = new MtEmployee();
		e2.setEmployeeCode("EMP_TEST_002");
		e2.setEmail("email2.com");
		
		// @formatter:off
		this.mockMvc.perform(MockMvcRequestBuilders.post(RequestURL.EMPLOYEE_DETAIL, "EMP_TEST_002")
													.contentType(TestUtils.APPLICATION_JSON_UTF8)
													.content(TestUtils.convertObjectToJsonBytes(e2))
													.with(SecurityMockMvcRequestPostProcessors.csrf()))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.content().contentType(TestUtils.APPLICATION_JSON_UTF8))
					.andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is(JsonResponse.RESPONSE_STATUS_FAIL)))
					.andExpect(MockMvcResultMatchers.jsonPath("$.result[*].field", Matchers.containsInAnyOrder("employeeName", "dateOfBirth", "email")))
					.andExpect(MockMvcResultMatchers.jsonPath("$.result[*].code", Matchers.containsInAnyOrder(this.messageServiceMock.getMessages("validator.employee_name_is_required"),
																												this.messageServiceMock.getMessages("validator.dob_is_required"), 
																												this.messageServiceMock.getMessages("validator.email_is_not_well_formed"))));
		// @formatter:on
	}
	
	@Test
	public void removeEmployeeTest_Success() throws Exception {
		Mockito.doNothing().when(this.employeeServiceMock).removeEmployee("EMP_TEST_001");
		// @formatter:off
				this.mockMvc.perform(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_REMOVE, "EMP_TEST_001"))
							.andExpect(MockMvcResultMatchers.status().isFound());
		// @formatter:on
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void removeEmployeeTest_NotFound() throws Exception {
		Mockito.when(this.messageServiceMock.getMessages("err.employee_is_not_found", "EMP_TEST_002")).thenReturn("Employee \"EMP_TEST_002\" is not found.", "EMP_TEST_002");
		Mockito.doThrow(new NotFoundException("Employee EMP_TEST_002 is not found.")).when(this.employeeServiceMock).removeEmployee("EMP_TEST_002");
		// @formatter:off
		this.mockMvc.perform(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_REMOVE, "EMP_TEST_002"))
					.andExpect(MockMvcResultMatchers.status().isNotFound())
					.andExpect(MockMvcResultMatchers.model().attribute("exception", Matchers.isA(NotFoundException.class)))
					.andExpect(MockMvcResultMatchers.model().attribute("exception", Matchers.anything(this.messageServiceMock.getMessages("err.employee_is_not_found", "EMP_TEST_002"))));
		// @formatter:on
	}
	
}
