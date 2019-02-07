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
	
	@Test 
	public void statmentForNoRental() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n"
	        + "Amount owed is 0.0\n"
	        + "You earned 0 frequent renter pointers"));
	}
	
	@Test 
	public void statementForRegularMovieRentalForLessThan3Days() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
	    int daysRented = 2;
	    Rental rental = new Rental(movie, daysRented);
	    customer.addRental(rental);

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t2.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 2.0\n"
	        + "You earned 1 frequent renter pointers"));
	} 
	
	@Test 
	public void statementForRegularMovieRentalForMoreThan2Days() {

	    // arrange
	    Customer customer = new Customer("NAME_NOT_IMPORTANT");
	    Movie movie = new Movie("TITLE_NOT_IMPORTANT", Movie.REGULAR);
	    int daysRented = 3;
	    Rental rental = new Rental(movie, daysRented);
	    customer.addRental(rental);

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t3.5(TITLE_NOT_IMPORTANT)\n"
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







	
}
