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
	
	@GetMapping("/")
	public String welcomePage(Model model) {
		Entry entry = new Entry();
		model.addAttribute("entry", entry);
		
		model.addAttribute("entries", entryService.getEntries());
		
		return "welcome";
	}
	
	
	@GetMapping("/updateEntry")
	public String updateEntry(@RequestParam("entryId") int entryId, Model model) {
		Entry entry = entryService.getEntry(entryId);
		model.addAttribute("entry", entry);
		return "entry-form";
	}
	
	@PostMapping("/processUpdatedEntry")
	public String saveUpdatedEntry(@Valid @ModelAttribute("entry") Entry entry, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "entry-form";
		}
		entryService.saveEntry(entry);
		return "redirect:/";
	} 
	
	@PostMapping("/processNewEntry")
	public String saveNewEntry(@Valid @ModelAttribute("entry") Entry entry, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("entries", entryService.getEntries());		
			return "welcome";
		}
		entryService.saveEntry(entry);
		return "redirect:/";
	} 
	
	@GetMapping("/deleteEntry")
	public String deleteEntry(@RequestParam("entryId") int entryId) {
		entryService.deleteEntry(entryId);
		return "redirect:/";
	}
}
