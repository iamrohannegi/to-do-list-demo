package com.todoapp.demo.service;

import java.util.List;
import com.todoapp.demo.entity.Entry;

//Entry Service interface that delegates the call to EntryDAO 
public interface EntryService {

	public List<Entry> getEntries();
	
	public Entry getEntry(int id);
	
	public void saveEntry(Entry entry);
	
	public void deleteEntry(int id);
}
