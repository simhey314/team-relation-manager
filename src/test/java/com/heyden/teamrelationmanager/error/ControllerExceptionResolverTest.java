package com.heyden.teamrelationmanager.error;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import javax.servlet.ServletContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.heyden.teamrelationmanager.config.WebAppConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {WebAppConfig.class})
@WebAppConfiguration
class ControllerExceptionResolverTest {

	@Autowired
	private WebApplicationContext webContext;
	private MockMvc mockMvc;

	@BeforeEach
	void setUpBeforeEach() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
	}
	
	@Test
	void testContextConfig() {
		ServletContext underTest = webContext.getServletContext();
		
		assertThat(underTest).isNotNull();
		assertThat(underTest).isInstanceOf(MockServletContext.class);
		assertThat(webContext.getBean("employeeController")).isNotNull();
	}

	@Test
	void testGetOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testResolveException() {
		fail("Not yet implemented");
	}
	
}
