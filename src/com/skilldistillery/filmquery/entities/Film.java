package com.skilldistillery.filmquery.entities;

import java.util.Set;

//Complete the Film class with attributes that map 
//to the columns of the film table. Use appropriate 
//datatypes and Java naming conventions. Provide getters 
//and setters, and appropriate constructors. Also add a good 
//toString, and equals and hashCode methods.
public class Film {
	private int filmId;
	private String title;
	private String desc;
	private int releaseYear;
	private int langId;
	private int rentDur;
	private double rentalRate;
	private int length;
	private double repCost;
	private String rating;
	private Set features;
	
	public Film(int filmId, String title, String description, int releaseYear, int languageId, int rentalDuration,
			double rentalRate, int length, double replacement_cost, String rating, Set specialFeatures) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rentalRate = rentalRate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
	}
	
	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return desc;
	}
	public void setDescription(String desc) {
		this.desc = desc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + ((features == null) ? 0 : features.hashCode());
		result = prime * result + filmId;
		result = prime * result + langId;
		result = prime * result + length;
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + releaseYear;
		result = prime * result + rentDur;
		long temp;
		temp = Double.doubleToLongBits(rentalRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(repCost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (features == null) {
			if (other.features != null)
				return false;
		} else if (!features.equals(other.features))
			return false;
		if (filmId != other.filmId)
			return false;
		if (langId != other.langId)
			return false;
		if (length != other.length)
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (releaseYear != other.releaseYear)
			return false;
		if (rentDur != other.rentDur)
			return false;
		if (Double.doubleToLongBits(rentalRate) != Double.doubleToLongBits(other.rentalRate))
			return false;
		if (Double.doubleToLongBits(repCost) != Double.doubleToLongBits(other.repCost))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public int getLanguageId() {
		return langId;
	}
	public void setLanguageId(int langId) {
		this.langId = langId;
	}
	public int getRentalDuration() {
		return rentDur;
	}
	public void setRentalDuration(int rentDur) {
		this.rentDur = rentDur;
	}
	public double getRentalRate() {
		return rentalRate;
	}
	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public double getReplacement_cost() {
		return repCost;
	}
	public void setReplacement_cost(double replacement_cost) {
		this.repCost = repCost;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public Set getSpecialFeatures() {
		return features;
	}
	public void setSpecialFeatures(Set specialFeatures) {
		this.features = features;
	}
	
	@Override
	public String toString() {
		return "Film:\t" + filmId + ", Title:\t" + title + ", Description:\t" + desc + ", Release Year:\t" + releaseYear
				+ ", Language Id:\t" + langId + ", Rental Duration:\t" + rentDur + ", Rental Rate:\t" + rentalRate
				+ ", Length:\t" + length + ", Replacement Cost:\t" + repCost + ", Rating:\t" + rating
				+ ", Special Features:\t" + features;
	}
	
	
	
}
