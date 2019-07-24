package com.todoapp.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.todoapp.demo.entity.Entry;

@Repository
public class EntryDaoImplementation implements EntryDao {

	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public List<Entry> getEntries() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		List<Entry> entries = new ArrayList<>();
		
		Query<Entry> entryQuery = currentSession.createQuery("from Entry where username=:username", Entry.class);
		entryQuery.setParameter("username", getCurrentUserName());
		
		entries = entryQuery.getResultList();
		
		return entries;
	}

	@Override
	public Entry getEntry(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Entry entry = currentSession.get(Entry.class, id);
		return entry;
	}
	
	@Override
	public void saveEntry(Entry entry) {
		Session currentSession = sessionFactory.getCurrentSession();
		entry.setUsername(getCurrentUserName());
		currentSession.saveOrUpdate(entry);
	}

	@Override
	public void deleteEntry(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Entry entry = currentSession.get(Entry.class, id);
		if(entry != null)
		{
			currentSession.remove(entry);
		}
	}
	
	private String getCurrentUserName() {
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}

}
