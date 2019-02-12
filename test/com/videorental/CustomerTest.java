package com.videorental;


import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {
	private static final String NAME = "NAME_NOT_IMPORTANT";
	private static final String TITLE = "TITLE_NOT_IMPORTANT";
	
	Customer customer = new Customer(NAME);
	
	@Test
	public void returnNewCustomer() {
		assertThat(customer, is(notNullValue()));
	}
	
	@Test
	public void statementForNoRental() {
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n"
				+ "Amount owed is 0.0\n"
				+ "You earned 0 frequent renter pointers"));
	}
	
	@Test
	public void statementForRegularMovieRentalForLessThan3Days() {
		
		customer.addRental(createRentalFor(Movie.REGULAR, 2));
		
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t2.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 2.0\n"
				+ "You earned 1 frequent renter pointers"));
	}
	
	@Test
	public void statementForRegularMovieRentalForMoreThan2Days() {
		
		customer.addRental(createRentalFor(Movie.REGULAR, 3));
		
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t3.5(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 3.5\n"
				+ "You earned 1 frequent renter pointers"));
	}

	@Test 
	public void statementForNewReleaseMovie() {

	    // arrange
	    
	    customer.addRental(createRentalFor(Movie.NEW_RELEASE, 1));

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t3.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 3.0\n"
	        + "You earned 1 frequent renter pointers"));
	}

	private Rental createRentalFor(int priceCode, int daysRented) {
		Movie movie = createMovie(priceCode);
	    Rental rental = new Rental(movie, daysRented);
		return rental;
	}

	private Movie createMovie(int priceCode) {
		switch (priceCode) {
		case Movie.REGULAR:
			return new RegularMovie(TITLE);
		case Movie.NEW_RELEASE:
			return new NewReleaseMovie(TITLE);
		case Movie.CHILDRENS:
			return new ChildrenMovie(TITLE);
		default:
			throw new IllegalArgumentException("Price code is not found");
		}
	}
	
	@Test 
	public void statementForNewReleaseMovieRentalMoreThan1Day() {

	    // arrange
	    
		customer.addRental(createRentalFor(Movie.NEW_RELEASE, 2));

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
		+ "\t6.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 6.0\n"
	        + "You earned 2 frequent renter pointers"));
	}
	
	@Test 
	public void statementForChildrensMovieRentalMoreThan3Days() {

	    // arrange
	    
		customer.addRental(createRentalFor(Movie.CHILDRENS, 4));

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
	    
		customer.addRental(createRentalFor(Movie.CHILDRENS, 3));

	    // act
	    // assert
	    assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t1.5(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 1.5\n"
	        + "You earned 1 frequent renter pointers"));
	}
	
	@Test 
	public void statementForFewMovieRental() {
		
		// arrange
		
		Movie regularMovie = new RegularMovie(TITLE);
		Movie newReleaseMovie = new NewReleaseMovie(TITLE);
		Movie childrenMovie = new ChildrenMovie(TITLE);
		
		customer.addRental(new Rental(regularMovie, 1));
		customer.addRental(new Rental(newReleaseMovie, 4));
		customer.addRental(new Rental(childrenMovie, 4));
		
		// act
		// assert
		assertThat(customer.statement(), is("Rental Record for NAME_NOT_IMPORTANT\n\t"
				+ "2.0(TITLE_NOT_IMPORTANT)\n\t"
				+ "12.0(TITLE_NOT_IMPORTANT)\n\t"
				+ "3.0(TITLE_NOT_IMPORTANT)\n"
				+ "Amount owed is 17.0\n"
				+ "You earned 4 frequent renter pointers"));
	}

}
