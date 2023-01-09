package entities;

public class Movie implements Comparable<Object> {
	private String movieID;
	private String title;
	private int minAge;

	public Movie(String movieID, String title, int minAge) {
		this.movieID = movieID;
		this.title = title;
		this.minAge = minAge;
	}

	@Override
	public String toString() {
		return String.format("ID: %s\n title: %s\n minAge: %d\n", movieID, title, minAge);
	}

	@Override
	public boolean equals(Object obj) {
		Movie m;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj.getClass() == this.getClass()) {
			m = (Movie) obj;
			return this.movieID == m.movieID;
		} else
			return false;
	}

	@Override
	public int compareTo(Object o) {
		return this.title.compareTo(((Movie) o).title);
	}
}
