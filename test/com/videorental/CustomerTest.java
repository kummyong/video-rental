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
	
	@Test 
	public void statementForNewReleaseMovie() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
	    int daysRented = 1;
	    Rental rental = new Rental(movie, daysRented);
	    customer.addRental(rental);

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t3.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 3.0\n"
	        + "You earned 1 frequent renter pointers"));
	}
	
	@Test 
	public void statementForNewReleaseMovieRentalMoreThan1Day() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
	    int daysRented = 2;
	    Rental rental = new Rental(movie, daysRented);
	    customer.addRental(rental);

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
		+ "\t6.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 6.0\n"
	        + "You earned 2 frequent renter pointers"));
	}
	
	@Test 
	public void statementForChildrensMovieRentalMoreThan3Days() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
	    int daysRented = 4;
	    Rental rental = new Rental(movie, daysRented);
	    customer.addRental(rental);

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t3.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 3.0\n"
	        + "You earned 1 frequent renter pointers"));
	}
	
	@Test 
	public void statementForChildrensMovieRentalLessThan4Days() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
	    int daysRented = 3;
	    Rental rental = new Rental(movie, daysRented);
	    customer.addRental(rental);

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t1.5(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 1.5\n"
	        + "You earned 1 frequent renter pointers"));
	}
	
	@Test 
	public void statementForFewMovieRental() {
		
		// arrange
		Customer customer = new Customer("NAME_NOT_IMPORTANT");
		
		Movie regularMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
		Movie newReleaseMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.NEW_RELEASE);
		Movie childrenMovie = new Movie("TITLE_NOT_IMPORTANT", Movie.CHILDRENS);
		
		customer.addRental(new Rental(regularMovie, 1));
		customer.addRental(new Rental(newReleaseMovie, 4));
		customer.addRental(new Rental(childrenMovie, 4));
		
		// act
		String statement = customer.statement();
		
		// assert
		assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "2.0(TITLE_NOT_IMPORTANT)\n\t"
				+ "12.0(TITLE_NOT_IMPORTANT)\n\t"
				+ "3.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 17.0\n"
				+ "You earned 4 frequent renter pointers"));
	}

}
