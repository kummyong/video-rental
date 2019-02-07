package com.videorental;

public class NewReleaseMovie extends Movie {

	public NewReleaseMovie(String title) {
		super(title, Movie.NEW_RELEASE);
	}

	@Override
	double getChargeFor(int daysRented) {
		return (double) (daysRented * 3);
	}
}
