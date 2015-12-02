package com.abnamro;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@RestResource(path="by-name")
	public Collection<Customer> findByLastName(@Param("lastName") String lastName);
}
