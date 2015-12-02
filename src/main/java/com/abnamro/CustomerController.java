package com.abnamro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class CustomerController {

	@Autowired

	private CustomerRepository customerRepository;

	@RequestMapping("/rest/customer/{id}")
	public Customer customer(@PathVariable("id") long id) {
		return customerRepository.findOne(id);
	}
	 
	
	@RequestMapping("/ui/customers/{id}")
	public String show(@PathVariable long id, ModelMap model) {
		Customer customer = customerRepository.findOne(id);
		if (customer == null) {
			throw new CustomerNotFoundException();
		}
		model.put("customer", customer);
		return "customers/show";
	}
	
	
	@ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "e404";
    }

}
