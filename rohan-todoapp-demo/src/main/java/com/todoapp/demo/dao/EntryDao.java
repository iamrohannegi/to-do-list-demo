package com.todoapp.demo.dao;

import java.util.List;

import com.todoapp.demo.entity.Entry;


//Interface to access or modify data on Entry entity in the database
public interface EntryDao {
	
	public List<Entry> getEntries();
	
	public Entry getEntry(int id);
	
	public void saveEntry(Entry entry);
	
	public void deleteEntry(int id);
}
