package com.spring.dal;

public interface MoviesDao
{
	public List<Movie> getAll() throws Exception;
	public void save(Movie movie) throws Exception;
	public void update(Movie movie) throws Exception;
	public void delete(String id) throws Exception;
	public Movie get(String id) throws Exception;
}
