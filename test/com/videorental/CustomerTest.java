package com.videorental;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {
	
	private static final String MOVIE_TITLE = "TITLE_NOT_IMPORTANT";
	private static final String CUSTOMER_NAME = "NAME_NOT_IMPORTANT";
	Customer customer = new Customer(CUSTOMER_NAME);
	
	@Test
	public void returnNewCustomer()	{
	    assertThat(customer, is(notNullValue()));
	}
	
	@Test 
	public void statmentForNoRental() {
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
	        + "Amount owed is 0.0\n"
	        + "You earned 0 frequent renter pointers"));
	}
	
	@Test 
	public void statementForRegularMovieRentalForLessThan3Days() {
	    // arrange
		createRentalFor(2, Movie.REGULAR);

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t2.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 2.0\n"
	        + "You earned 1 frequent renter pointers"));
	}

	
	@Test 
	public void statementForRegularMovieRentalForMoreThan2Days() {
	    // arrange
		createRentalFor(3, Movie.REGULAR);

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t3.5(TITLE_NOT_IMPORTANT)\n"
	        + "Amount owed is 3.5\n"
	        + "You earned 1 frequent renter pointers"));
	}
	
	@Test 
	public void statementForNewReleaseMovie() {
	    // arrange
		createRentalFor(1, Movie.NEW_RELEASE);

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t3.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 3.0\n"
	        + "You earned 1 frequent renter pointers"));
	}
	
	@Test 
	public void statementForChildrensMovieRentalMoreThan3Days() {
	    // arrange
		createRentalFor(4, Movie.CHILDRENS);

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t3.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 3.0\n"
	        + "You earned 1 frequent renter pointers"));
	}
	
	@Test 
	public void statementForChildrensMovieRentalLessThan4Days() {
	    // arrange
	    createRentalFor(3, Movie.CHILDRENS);

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t1.5(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 1.5\n"
	        + "You earned 1 frequent renter pointers"));
	}
	
	@Test 
	public void statementForNewReleaseMovieRentalMoreThan1Day() {
	    // arrange
		createRentalFor(2, Movie.NEW_RELEASE);

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
		+ "\t6.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 6.0\n"
	        + "You earned 2 frequent renter pointers"));
	}
	
	@Test 
	public void statementForFewMovieRental() {
		createRentalFor(1, Movie.REGULAR);
		createRentalFor(4, Movie.NEW_RELEASE);
		createRentalFor(4, Movie.CHILDRENS);
	
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t2.0(TITLE_NOT_IMPORTANT)\n"
	        + "\t12.0(TITLE_NOT_IMPORTANT)\n"
	        + "\t3.0(TITLE_NOT_IMPORTANT)\n"
	        + "Amount owed is 17.0\n"
	        + "You earned 4 frequent renter pointers"));
	}
	
	@Test
	public void statementForNoPriceCodeMovieRental()	{
	    // arrange
		createRentalFor(1, -1);

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
		+ "\t0.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 0.0\n"
	        + "You earned 1 frequent renter pointers"));
	}

	private void createRentalFor(int daysRented, int priceCode) {
		Movie movie = createMovie(priceCode);
		Rental rental = new Rental(movie, daysRented);
		customer.addRental(rental);
	}

	private Movie createMovie(int priceCode) {
		switch(priceCode) {
		case Movie.REGULAR:
			return new RegularMovie(MOVIE_TITLE);
		case Movie.NEW_RELEASE:
			return new NewReleaseMovie(MOVIE_TITLE);
		case Movie.CHILDRENS:
			return new ChildrenMovie(MOVIE_TITLE);
		default : 
			return new Movie(MOVIE_TITLE, priceCode);	
		}
	} 
}
