package com.giong.test.web.controller;


import javax.servlet.Filter;

import org.junit.Before;
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
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.giong.config.context.ApplicationContext;
import com.giong.constant.RequestURL;
import com.github.springtestdbunit.DbUnitTestExecutionListener;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { ApplicationContext.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
@WithMockUser(authorities = { "VIEW_EMP", "EXE_EMP" })
public class EmployeeIntegrationTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup() {
		// @formatter:off
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
										.alwaysDo(MockMvcResultHandlers.print())
										.defaultRequest(MockMvcRequestBuilders.get(RequestURL.EMPLOYEE_SUMMARY)
																				.with(SecurityMockMvcRequestPostProcessors.testSecurityContext()))
										.addFilter(this.springSecurityFilterChain)
										.build();
		// @formatter:on
	}
}
