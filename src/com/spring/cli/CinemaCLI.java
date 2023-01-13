package com.spring.cli;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.entities.Movie;
import com.spring.entities.Session;
import com.spring.service.MovieService;
import com.spring.service.SessionService;

public class CinemaCLI {
	static SessionService Theaters;
	static MovieService Movies;

	public static void initBeans() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Theaters = context.getBean("SessionService", SessionService.class);
		Movies = context.getBean("MovieService", MovieService.class);
	}

	// print massage and return user's input - string
	public static int getInputInt(String massage) {
		System.out.println(massage);
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

	// print massage and return user's input - string
	public static String getInputString(String massage) {
		System.out.println(massage);
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	// print menu and return user's selection - int
	public static int menu() {
		System.out.println("HI! welcome to our Cinema :) \n please select an option from the menu:"
				+ "\n  1. Show movies" + "\n  2. Show movie theaters" + "\n  3. Add a movie" + "\n  4. Update a movie"
				+ "\n  5. Delete a movie" + "\n  6. Add a theater" + "\n  7. Update a theater"
				+ "\n  8. Delete a theater" + "\n  9. Exit" + "\n");
		int selection = getInputInt("\nYour selection:");
		// checks if the selection is legal, else re-inputs it
		while (selection <= 0 || (selection > 8 && selection != 9)) {
			selection = getInputInt("illegal selection");
		}
		return selection;
	}

	// _____Show Movies_____
	public static void ShowMovies() {
		System.out.println("_____Show Movies_____");
		List<Movie> allMovies = null;
		try {
			allMovies = Movies.getAll();
			for (int i = 0; i < allMovies.size(); i++) {
				System.out.println(allMovies.get(i));
				List<Session> allTheaters = Theaters.getAll();
				// print theater the movie is projected in
				for (int j = 0; j < allTheaters.size(); j++) {
					if (allTheaters.get(j).getMovieID().equals(allMovies.get(i).getMovieID()))
						System.out.println("The movie is projected n these theater: " + allMovies.get(i));
					System.out.println("");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return;
		}

	}

	// _____Show Movie Theaters_____
	public static void ShowMovieTheaters() {
		System.out.println("_____Show Movie Theaters_____");
		List<Session> allTheaters = null;
		try {
			allTheaters = Theaters.getAll();
			for (int i = 0; i < allTheaters.size(); i++) {
				System.out.println(allTheaters.get(i));
				// print the movie projected
				List<Movie> allMovies = Movies.getAll();
				for (int j = 0; j < allMovies.size(); j++) {
					if (allTheaters.get(i).getMovieID().equals(allMovies.get(j).getMovieID()))
						System.out.println("The movie is projected n these theater: " + allMovies.get(j));
				}
				System.out.println("");
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return;
		}

	}

	// _____Add Theater_____
	public static void AddTheater() {
		System.out.println("_____Add Theater_____");
		String sessionID = getInputString("please enter theater id: ");
		String hallID = getInputString("please enter hall id: ");
		String date = getInputString("please enter date: ");
		String time = getInputString("please enter time: ");
		int capacity = getInputInt("please enter capacity: ");
		int ticketPrice = getInputInt("please enter ticket price: ");
		String movieID = getInputString("please enter movie id: ");
		Session toAdd = new Session(sessionID, hallID, date, time, capacity, (float) ticketPrice, movieID);
		try {
			Theaters.save(toAdd);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return;
		}

	}

	// _____Update Theater_____
	public static void UpdateTheater() {
		System.out.println("_____Update Theater_____");
		String theaterID = getInputString("please enter updated theater id: ");
		String hallID = getInputString("please enter updated hall id: ");
		String date = getInputString("please enter updated date: ");
		String time = getInputString("please enter updated time: ");
		int capacity = getInputInt("please enter updated capacity: ");
		int ticketPrice = getInputInt("please enter updated ticket price: ");
		String movieID = getInputString("please enter updated movie id: ");
		try {
			Session toUpdate = new Session(theaterID, hallID, date, time, capacity, (float) ticketPrice, movieID);
			Theaters.update(toUpdate);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return;
		}

	}

	// _____Delete Movies_____
	public static void DeleteTheater() {
		System.out.println("_____Delete Theater_____");
		String theaterID = getInputString("please enter movie id: ");
		try {
			Theaters.delete(theaterID);
			System.out.println("theater deleted");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return;
		}
	}

	// _____Add Movie_____
	public static void AddMovie() {
		System.out.println("_____Add Movie_____");
		String movieID = getInputString("please enter movie id: ");
		String title = getInputString("please enter movies title: ");
		int minAge = getInputInt("please enter movie age limit: ");
		Movie toAdd = new Movie(movieID, title, minAge);
		try {
			Movies.save(toAdd);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return;
		}

	}

	// _____Update Movies_____
	public static void UpdateMovies() {
		System.out.println("_____Update Movies_____");
		String movieID = getInputString("please enter movie id: ");
		String title = getInputString("please enter updated movies title: ");
		int minAge = getInputInt("please enter updated movie age limit: ");
		try {
			Movie toUpdate = new Movie(movieID, title, minAge);
			Movies.update(toUpdate);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return;
		}

	}

	// _____Delete Movies_____
	public static void DeleteMovies() {
		System.out.println("_____Delete Movies_____");
		String movieID = getInputString("please enter movie id: ");
		try {
			Movies.delete(movieID);
			System.out.println("movie deleted");
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			return;
		}
	}

	public static void main(String args[]) // static method
	{
		//init cinema
		initBeans(); // generate
		Theaters.setMaxSessions(5); // num of theaters in cinema
		Theaters.setMaxCapacityInCinema(500);

		// ------------------------menu-------------------------------------

		int selection;
		while (true) {
			selection = menu();
			switch (selection) {
			case 9:
				System.out.println("--Exit-- \n" + "        GoodBye");
				System.exit(0);
			case 1:
				ShowMovies();
			case 2:
				ShowMovieTheaters();
			case 3:
				AddMovie();
			case 4:
				UpdateMovies();
			case 5:
				DeleteMovies();
			case 6:
				AddTheater();
			case 7:
				UpdateTheater();
			case 8:
				DeleteTheater();
			default:
				System.out.println("Error - menu selection");
			}
		}
	}
}
