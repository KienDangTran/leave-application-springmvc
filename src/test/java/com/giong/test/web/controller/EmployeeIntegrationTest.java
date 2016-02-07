package com.giong.test.web.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.Filter;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.giong.config.context.ApplicationContext;
import com.giong.constant.RequestURL;
import com.giong.constant.View;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationContext.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@WithMockUser(authorities = { "VIEW_EMP", "EXE_EMP" })
@DatabaseSetup("/fullTestData.xml")
public class EmployeeIntegrationTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private Filter springSecurityFilterChain;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders	.webAppContextSetup(this.context).alwaysDo(MockMvcResultHandlers.print())
										.defaultRequest(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_SUMMARY).with(SecurityMockMvcRequestPostProcessors.testSecurityContext()))
										.addFilter(this.springSecurityFilterChain).build();
	}

	@Test
	@ExpectedDatabase("/fullTestData.xml")
	public void testGetALlEmployees() throws ParseException, Exception {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		this.mockMvc.perform(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_SUMMARY)).andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name(View.EMPLOYEE_SUMMARY.getViewName())).andExpect(MockMvcResultMatchers.model().attribute("allEmployees", Matchers.hasSize(4)));
		//					.andExpect(MockMvcResultMatchers.model().attribute(	"allEmployees",
		//																		Matchers.hasItem(Matchers.allOf(Matchers.hasProperty("employeeCode", Matchers.is("EMP0000001")),
		//																										Matchers.hasProperty("employeeName", Matchers.is("Sample Employee 1")),
		//																										Matchers.hasProperty("sex", Matchers.is("M")),
		//																										Matchers.hasProperty("dateOfBirth", Matchers.is(sdf.parse("1991-11-01"))),
		//																										Matchers.hasProperty("phoneNo", Matchers.is("0943432243")),
		//																										Matchers.hasProperty("email", Matchers.is("emp0000001@lvapp.com"))))))
		//					.andExpect(MockMvcResultMatchers.model().attribute(	"allEmployees",
		//																		Matchers.hasItem(Matchers.allOf(Matchers.hasProperty("employeeCode", Matchers.is("EMP0000002")),
		//																										Matchers.hasProperty("employeeName", Matchers.is("Sample Employee 2")),
		//																										Matchers.hasProperty("sex", Matchers.is("F")),
		//																										Matchers.hasProperty("dateOfBirth", Matchers.is(sdf.parse("1991-11-02"))),
		//																										Matchers.hasProperty("phoneNo", Matchers.is("0943432243")),
		//																										Matchers.hasProperty("email", Matchers.is("emp0000001@lvapp.com"))))));
	}
}
