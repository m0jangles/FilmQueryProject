package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	private Object app;
	String answer;
	Film film;

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

		System.out.println("Would you like to try the Film Query App?\n"
				+ "It will show you information about movies you've\n" + "never heard of?");
		System.out.println("You should choose (Y)");
		System.out.println("Your Choice:\t");
		String answer = input.next();
		if (answer.equalsIgnoreCase("Y")) {
			startUserInterface(input);

		} else if (answer.equalsIgnoreCase("N")) {
			System.out.println("Goodbye.");
			System.exit(0);
		} else {
			System.out.println("I did not understand your answer :( \n" + "TRY AGAIN...");
			launch();
		}

		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		int choice = 0;

		System.out.println("Film Query Menu");
		while (choice != 3) {

			System.out.println("1. Look up film by ID");
			System.out.println("2. Look up film by search keyword");
			System.out.println("3. Quit!");
			System.out.print("\nYour choice: ");
			choice = input.nextInt();

			switch (choice) {
			case 1:
				System.out.println("Enter a film ID: ");
				int id = input.nextInt();
				film = db.getFilmById(id);
				System.out.println(film);
				break;
			case 2:
				System.out.println("Enter a keyword");
				String keyword = input.next();
				film = db.getFilmByKeyword(keyword);
				System.out.println(film);				
				break;
			case 3:
				System.out.println("Goodbye.");
				System.exit(0);
			default:
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
