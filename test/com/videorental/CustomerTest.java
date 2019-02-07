package com.videorental;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {
	
	@Test
	public void returnNewCustomer()	{
		Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    
	    assertThat(customer, is(notNullValue()));
	}
	
}
