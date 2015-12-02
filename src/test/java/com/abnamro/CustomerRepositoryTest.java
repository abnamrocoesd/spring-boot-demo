package com.abnamro;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AbndemoApplication.class)
@WebIntegrationTest // needed after adding swagger 
public class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository repository;

	@Test
	public void testFindByLastName() {
		Assert.assertThat(repository.findByLastName("Kumar").size(), CoreMatchers.equalTo(0));

		repository.save(new Customer("Assen", "Kumar"));

		Assert.assertThat(repository.findByLastName("Kumar").size(), CoreMatchers.equalTo(1));

		Assert.assertThat(repository.findByLastName("Costanza").size(), CoreMatchers.equalTo(2));

	}

}