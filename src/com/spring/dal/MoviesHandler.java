package com.spring.dal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.entities.Movie;
import com.spring.serialization.Serialization;
@Component("MoviesHandler")
public class MoviesHandler implements MoviesDao
{
	public static String fileName = "C:\\Users\\Administrator\\Documents\\GitHub\\JAVAProj\\movies.dat";
	public static Serialization<Movie> Serialzator;
	public MoviesHandler()
	{
		Serialzator = new Serialization<Movie>();
	}
	@Override
	public List<Movie> getAll() throws Exception
	{
		ArrayList<Movie> list = Serialzator.deserialize(fileName);
		Serialzator.deserialize(fileName);
		return list;
	}
	@Override
	public void save(Movie movie) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		list.add(movie);
		
		
	}
	@Override
	public void update(Movie movie) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		for(Movie m : list)
		{
			if(m.getMovieID() == (movie.getMovieID()))
			{
				m = movie;
			}
		}
		
	}
	@Override
	public Movie get(String id) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		for(Movie m : list)
		{
			if(m.getMovieID() == id)
			{
				return m; 
			}
		}
		
		return null;
	}
	@Override
	public void delete(String id) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		for(Movie m : list)
		{
			if(m.getMovieID() == id)
			{
				list.remove(m);
			}
		}
		
	}
}
