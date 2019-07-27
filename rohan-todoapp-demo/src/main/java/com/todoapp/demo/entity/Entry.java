package com.todoapp.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "entry")
public class Entry {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;

	@Column(name="username")
	private String username;
	
	@NotNull(message="*is required")
	@Size(min=1, message="is required")
	@Size(max=40, message=" can't be greater than 40 characters.")
	@Column(name="entry_text")
	private String entryText;
	
	
	public Entry() {}

	public Entry(String entryText) {
		this.entryText = entryText;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEntryText() {
		return entryText;
	}

	public void setEntryText(String entryText) {
		this.entryText = entryText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
