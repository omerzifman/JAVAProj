package com.spring.dal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import com.spring.entities.Movie;
import com.spring.entities.Session;
import com.spring.serialization.Serialization;
@Component("SessionFileDao")
public class SessionFileDao implements SessionsDao
{
	public static String fileName = ".\\sessions.dat";
	public static Serialization<Session> Serialzator;
	public SessionFileDao()
	{
		Serialzator = new Serialization<Session>();
	}
	@Override
	public List<Session> getAll() throws Exception
	{
		ArrayList<Session> list = Serialzator.deserialize(fileName);
		Collections.sort(list);
		return list;
	}
	@Override
	public void save(Session session) throws Exception
	{
		List<Session> list = Serialzator.deserialize(fileName);
		list.add(session);
		Serialzator.serialize(fileName, (ArrayList<Session>) list);
		
		
	}
	@Override
	public void update(Session session) throws Exception
	{
		List<Session> list = Serialzator.deserialize(fileName);
		for(Session s : list)
		{
			if(s.getSessionID() == (session.getSessionID()))
			{
				s = session;
			}
		}
		Serialzator.serialize(fileName, (ArrayList<Session>) list);
		
	}
	@Override
	public Session get(String id) throws Exception
	{
		List<Session> list = Serialzator.deserialize(fileName);
		for(Session s : list)
		{
			if(s.getSessionID() == id)
			{
				return s; 
			}
		}
		
		return null;
	}
	@Override
	public void delete(String id) throws Exception
	{
		List<Session> list = Serialzator.deserialize(fileName);
		for(Session s : list)
		{
			if(s.getSessionID() == id)
			{
				list.remove(s);
			}
		}
		Serialzator.serialize(fileName, (ArrayList<Session>) list);
		
	}
}
