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

	    // arrange

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
	    Movie movie = new Movie(MOVIE_TITLE, Movie.REGULAR);
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
	    Movie movie = new Movie(MOVIE_TITLE, Movie.REGULAR);
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
	    Movie movie = new Movie(MOVIE_TITLE, Movie.NEW_RELEASE);
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
	    Movie movie = new Movie(MOVIE_TITLE, Movie.CHILDRENS);
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
	    Movie movie = new Movie(MOVIE_TITLE, Movie.CHILDRENS);
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
	    Movie movie = new Movie(MOVIE_TITLE, Movie.NEW_RELEASE);
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
	public void statementForFewMovieRental() {

	    Movie regularMovie = new Movie(MOVIE_TITLE, Movie.REGULAR);
	    Movie newReleaseMovie = new Movie(MOVIE_TITLE, Movie.NEW_RELEASE);
	    Movie childrensMovie = new Movie(MOVIE_TITLE, Movie.CHILDRENS);
	    customer.addRental(new Rental(regularMovie, 1));
	    customer.addRental(new Rental(newReleaseMovie, 4));
	    customer.addRental(new Rental(childrensMovie, 4));

	    String statement = customer.statement();

	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
	        + "\t2.0(TITLE_NOT_IMPORTANT)\n"
	        + "\t12.0(TITLE_NOT_IMPORTANT)\n"
	        + "\t3.0(TITLE_NOT_IMPORTANT)\n"
	        + "Amount owed is 17.0\n"
	        + "You earned 4 frequent renter pointers"));
	}
	
	@Test
	public void statementForNoPriceCodeMovieRental()	{
	    // arrange
	    Movie movie = new Movie(MOVIE_TITLE, -1);
	    int daysRented = 1;
	    Rental rental = new Rental(movie, daysRented);
	    customer.addRental(rental);

	    // act
	    String statement = customer.statement();

	    // assert
	    assertThat(statement, is("Rental Record for NAME_NOT_IMPORTANT\n" 
		+ "\t0.0(TITLE_NOT_IMPORTANT)\n" 
	        + "Amount owed is 0.0\n"
	        + "You earned 1 frequent renter pointers"));
	}








	
}
