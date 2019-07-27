package com.todoapp.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.todoapp.demo.entity.Entry;
import com.todoapp.demo.service.EntryService;

@Controller
public class MainController {
	
	@Autowired
	private EntryService entryService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		//Trim trailing white-spaces in strings and trim empty strings to null
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	//Show To-do list
	@GetMapping("/")
	public String welcomePage(Model model) {
		//Add an empty entry to add new to-do entry to the database
		Entry entry = new Entry();
		model.addAttribute("entry", entry);
		
		//Add all to-do entries for the user
		model.addAttribute("entries", entryService.getEntries());
		
		return "welcome";
	}
	
	
	//show entry-form to update To-do entry
	@GetMapping("/updateEntry")
	public String updateEntry(@RequestParam("entryId") int entryId, Model model) {
		//Add entry to be updated.
		Entry entry = entryService.getEntry(entryId);
		model.addAttribute("entry", entry);
		
		return "entry-form";
	}
	
	//Update existing entry
	@PostMapping("/processUpdatedEntry")
	public String saveUpdatedEntry(@Valid @ModelAttribute("entry") Entry entry, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "entry-form";
		}
		entryService.saveEntry(entry);
		return "redirect:/";
	} 
	
	//Add new entry
	@PostMapping("/processNewEntry")
	public String saveNewEntry(@Valid @ModelAttribute("entry") Entry entry, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("entries", entryService.getEntries());		
			return "welcome";
		}
		entryService.saveEntry(entry);
		return "redirect:/";
	} 
	
	//Delete entry based on entry id
	@GetMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("entryId") int entryId) {
		entryService.deleteEntry(entryId);
		return "redirect:/";
	}
}
