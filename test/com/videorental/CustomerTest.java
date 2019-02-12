package com.videorental;


import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {
	
	@Test
	public void returnNewCustomer() {
		Customer customer = new Customer("NAME_NOT_IMPORTANT");
		assertThat(customer, is(notNullValue()));
	}
	
	@Test
	public void statementForNoRental() {
		Customer customer = new Customer("NAME_NOT_IMPORTANT");
		String statement = customer.statement();
		assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n"
				+ "Amount owed is 0.0\n"
				+ "You earned 0 frequent renter pointers"));
	}
	
	@Test
	public void statementForRegularMovieRentalForLessThan3Days() {
		Customer customer = new Customer("NAME_NOT_IMPORTANT");
		
		Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
		int dayRented = 2;
		Rental rental = new Rental(movie, dayRented);
		customer.addRental(rental);
		
		String statement = customer.statement();
		
		assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n\t2.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 2.0\n"
				+ "You earned 1 frequent renter pointers"));
	}
	
	@Test
	public void statementForRegularMovieRentalForMoreThan2Days() {
		Customer customer = new Customer("NAME_NOT_IMPORTANT");
		
		Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
		int dayRented = 3;
		Rental rental = new Rental(movie, dayRented);
		customer.addRental(rental);
		
		String statement = customer.statement();
		
		assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n\t3.5(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 3.5\n"
				+ "You earned 1 frequent renter pointers"));
	}
}
