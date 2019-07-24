package com.todoapp.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todoapp.demo.dao.EntryDao;
import com.todoapp.demo.entity.Entry;

@Service
public class EntryServiceImplementation implements EntryService {

	@Autowired
	private EntryDao entryDao;
	
	@Override
	@Transactional
	public List<Entry> getEntries() {
		return entryDao.getEntries();
	}

	@Override
	@Transactional
	public void saveEntry(Entry entry) {
		entryDao.saveEntry(entry);
	}

	@Override
	@Transactional
	public void deleteEntry(int id) {
		entryDao.deleteEntry(id);
	}
	
	@Override
	@Transactional
	public Entry getEntry(int id) {
		return entryDao.getEntry(id);
	}
}
