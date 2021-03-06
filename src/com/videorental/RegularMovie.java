package com.videorental;

public class RegularMovie extends Movie {

	public RegularMovie(String title) {
		super(title, Movie.REGULAR);
	}
	
	double getChargeFor(int daysRented) {
		double thisAmount = 0;
		thisAmount += 2;
		if (daysRented > 2)
			thisAmount += (daysRented - 2) * 1.5;
		return thisAmount;
	}
	
	int getFrequentRenterPoints(int daysRented) {
		return 1;
	}

}
