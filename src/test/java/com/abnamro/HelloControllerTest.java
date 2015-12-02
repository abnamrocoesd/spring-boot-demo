package com.abnamro;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AbndemoApplication.class)
@WebIntegrationTest(randomPort = true)
public class HelloControllerTest {

	private RestTemplate restTemplate = new TestRestTemplate("abn", "abn");

	@Value("${local.server.port}")
	private String port;

	@Test
	public void testHello() throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/hello",
				String.class);
		Assert.assertThat(response.getBody(), CoreMatchers.startsWith("hello"));
	}
	
	@Test
	public void testShow() throws Exception {
		CustomerController controller = new CustomerController();
		CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
		Mockito.when(customerRepository.findOne(1L)).thenReturn(new Customer("John", "Zalm"));
		ReflectionTestUtils.setField(controller, "customerRepository", customerRepository);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		mockMvc.perform(MockMvcRequestBuilders.get("/ui/customers/1"))
				.andExpect(MockMvcResultMatchers.model().attributeExists("customer"))
				.andExpect(MockMvcResultMatchers.view().name("customers/show"));
	}

}