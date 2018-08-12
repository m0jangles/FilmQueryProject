package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	private Object app;
	String answer;
	Film film;
	List<Film> films;
	List<Actor> actors;

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		FilmQueryApp app = new FilmQueryApp();
//		app.test();

		app.launch();
	}

//	private void test() throws SQLException {
//		Film film = db.getFilmById(1);
//		System.out.println(film);
//	}

	public void launch() throws SQLException {
		Scanner input = new Scanner(System.in);
		// User is prompted with question to start the app
		System.out.println("Would you like to try the Film Query App?\n"
				+ "It will show you information about movies you've\n" + "never heard of.");
		System.out.println("You should choose (Y)");
		System.out.println("Your Choice:\t");
		String answer = input.next();
		// check answer to either proceed with running the app or exiting
		if (answer.equalsIgnoreCase("Y")) {
			startUserInterface(input);

		} else if (answer.equalsIgnoreCase("N")) {
			System.out.println("Goodbye.");
			System.exit(0);
		} else {
			// check for invalid input and try to encourage user to enter valid input
			// should alternatively develop a try catch for this, but works for now
			System.out.println("I did not understand your answer :( \n" + "TRY AGAIN...");
			launch();
		}

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		String choice = "";
		// Show user a menu for options to search for titles
		System.out.println("Welcome to Movie Town\n");
		System.out.println("Make a selection:\n");

		while (choice != "3") {

			System.out.println("1. Look up film by ID");
			System.out.println("2. Look up film by search keyword");
			System.out.println("3. Quit!");
			System.out.print("\nYour choice: ");
			choice = input.next();
			// Make sure the input is an integer because that's
			// what I want to use for the switch
			try {
				int check = Integer.parseInt(choice);
			} catch (NumberFormatException e) {
				System.out.println("Input must be an INTEGER");
			}

			switch (choice) {
			case "1":
				System.out.println("Look up a movie by ID. Enter a valid ID:\t");
				int id = input.nextInt();
				
				film = db.getFilmById(id);
				if (film == null) {
					System.out.println("ERROR! You have encountered one of the following problems:\n"
							+ " 1) That ID does not exist.\n" + " 2) That ID is NULL\n" + " 3) See reason #2\n"
							+ " NOTE: NULL is the equivalent of \"I don't know\"\n"
							+ " NOTES CONTINUED: I once heard a very talented programmer explain NULL as such:\n"
							+ " NULL is like an empty toilet paper roll; it is something but it isn't anything.  It is\n"
							+ " there but it isn't.  It is NULL.\n\n Choose another option: ");

				} else {
					System.out.println("Title:\t" + film.getTitle() + "\nRelease Year:\t" + film.getReleaseYear()
							+ "\nRating:\t" + film.getRating() + "\nDescription:\t" + film.getDesc() + "\nLanguage:\t"
							+ film.getLanguage() + "\n" + film.getActors());
					System.out.println("Choose another option:\n");
				}
				break;
			case "2":
				System.out.println("Enter a keyword");
				String keyword = input.next();
				films = db.getFilmsByKeyword(keyword);
				if (films.size() == 0) {
					System.out.println("NO MOVIES MATCHED THAT KEYWORD :(  TRY AGAIN.");
					System.out.println("Choose another option:\n");
				} else {
					for (Film film : films) {
						System.out.println("Title:\t" + film.getTitle() + "\nRelease Year:\t" + film.getReleaseYear()
								+ "\nRating:\t" + film.getRating() + "\nDescription:\t" + film.getDesc()
								+ film.getLanguage() + "\n" + film.getActors());

					}
					System.out.println("Choose another option");
					System.out.println();
				}

				break;
			case "3":
				System.out.println("Goodbye.");
				System.exit(0);
			default:
				// Try to catch anything outside the range I'm looking for
				System.out.println("INVALID ENTRY");
				pickAgain();
				break;

			}
		}
	}

	private void pickAgain() {
		System.out.println("\nTry again: ");
	}


}
