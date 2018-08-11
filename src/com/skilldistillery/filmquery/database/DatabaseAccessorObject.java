package com.skilldistillery.filmquery.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private String user = "student";
	private String pass = "student";
	private String keyword;

	@Override
	public Film getFilmById(int filmId) throws SQLException {
		Film film = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration,"
				+ "rental_rate, length, replacement_cost, rating, special_features FROM film WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, filmId);
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setFilmId(filmResult.getInt(1));
			film.setTitle(filmResult.getString(2));
			film.setDesc(filmResult.getString(3));
			film.setReleaseYear(filmResult.getShort(4));
			film.setLangId(filmResult.getInt(5));
			film.setRentDur(filmResult.getInt(6));
			film.setRentalRate(filmResult.getDouble(7));
			film.setLength(filmResult.getInt(8));
			film.setRepCost(filmResult.getDouble(9));
			film.setRating(filmResult.getString(10));
			film.setFeatures(filmResult.getString(11));
			film.setActors(getActorsByFilmId(filmId)); // An Actor has Films

		}
		// ...
		return film;
	}

	@Override
	public Actor getActorById(int actorId) throws SQLException {

		Actor actor = null;

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(); // Create the object
			// Here is our mapping of query columns to our object fields:
			actor.setActorId(actorResult.getInt(1));
			actor.setFirstName(actorResult.getString(2));
			actor.setLastName(actorResult.getString(3));
			actor.setFilms(getFilmsByActorId(actorId)); // An Actor has Films
		}
		// ...
		return actor;
	}

	@Override
	public List<Actor> getActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();

		try {
//			
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name FROM actor JOIN film_actor ON "
					+ "film_actor.actor_id = actor.id WHERE film_actor.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int actorId = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);

				Actor actor = new Actor(actorId, firstName, lastName);
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public Film getFilmByKeyword(String keyword) throws SQLException {
		Film film = null;
		
		

		Connection conn = DriverManager.getConnection(URL, user, pass);
		String sql = "SELECT id, title, description, release_year, language_id, rental_duration,"
				+ "rental_rate, length, replacement_cost, rating, special_features FROM film WHERE film.title LIKE ?";
		
		
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+ keyword + "%");
		ResultSet filmResult = stmt.executeQuery();
		if (filmResult.next()) {
			film = new Film(); // Create the object
			// Here is our mapping of query columns to our object fields:
			film.setFilmId(filmResult.getInt(1));
			film.setTitle(filmResult.getString(2));
			film.setDesc(filmResult.getString(3));
			film.setReleaseYear(filmResult.getShort(4));
			film.setLangId(filmResult.getInt(5));
			film.setRentDur(filmResult.getInt(6));
			film.setRentalRate(filmResult.getDouble(7));
			film.setLength(filmResult.getInt(8));
			film.setRepCost(filmResult.getDouble(9));
			film.setRating(filmResult.getString(10));
			film.setFeatures(filmResult.getString(11));
			film.setActors(getActorsByFilmId(film.getFilmId())); // An Actor has Films

		}
		// ...
		return film;
	}

	@Override
	public List<Film> getFilmsByActorId(int actorId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
