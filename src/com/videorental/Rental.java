package com.videorental;

class Rental {
	private Movie movie;
	private int daysRented;

	public Rental(Movie movie, int daysRented) {
		this.movie = movie;
		this.daysRented = daysRented;
	}

	public int getDaysRented() {
		return daysRented;
	}

	public Movie getMovie() {
		return movie;
	}

	double getCharge() {
		return movie.getChargeFor(getDaysRented());
	}

	int frequentRenterPointsFor() {
		return movie.getFrequentRenterPoints(getDaysRented());
	}
}
