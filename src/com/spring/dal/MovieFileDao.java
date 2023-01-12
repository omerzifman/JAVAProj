package com.spring.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.entities.Movie;
import com.spring.serialization.Serialization;
@Component("MovieFileDao")
public class MovieFileDao implements MoviesDao
{
	public static String fileName = ".\\movies.dat";
	public static Serialization<Movie> Serialzator;
	public MovieFileDao()
	{
		Serialzator = new Serialization<Movie>();
	}
	@Override
	public List<Movie> getAll() throws Exception
	{
		ArrayList<Movie> list = Serialzator.deserialize(fileName);
		Collections.sort(list);
		return list;
	}
	@Override
	public void save(Movie movie) throws Exception
	{
		List<Movie> list = Serialzator.deserialize(fileName);
		list.add(movie);
		Serialzator.serialize(fileName, (ArrayList<Movie>) list);	
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
		Serialzator.serialize(fileName, (ArrayList<Movie>) list);
		
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
		Serialzator.serialize(fileName, (ArrayList<Movie>) list);
		
	}
}
