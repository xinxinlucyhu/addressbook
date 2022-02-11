package com.tts.adressbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddressBookController {
	@Autowired
	private EntryRepository entryRepository;

	@GetMapping(value = "/")
	public String index() {
		return "addresspost/index";
	}

	@GetMapping(value = "/add")
	public String add(Model model, Entry entry) {

		return "addresspost/add";
	}

	@PostMapping(value = "/added")
	public String added(@Valid Entry entry, BindingResult result, Model model) {
		if (result.hasErrors()) {
			System.out.println(entry);
			model.addAttribute("entry", entry);
			return "addresspost/index";
		}

		try {
			entryRepository.save(entry);
			model.addAttribute("entry", entry);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println(model.getAttribute("entry"));
			return "addresspost/index";
		}
		return "addresspost/added";
	}

	@GetMapping(value = "/print")
	public String print(Model model) {

		List<Entry> entryList = new ArrayList<>();
		for (Entry item : entryRepository.findAll()) {
			entryList.add(item);
		}
		model.addAttribute("entry", entryList);
		System.out.println(entryList);
		return "addresspost/print";
	}

	@GetMapping(value = "/delete")
	public String delete() {
		entryRepository.deleteAll();
		return "addresspost/deleted";
	}

	@GetMapping(value = "/rules")
	public String rules() {
		return "addresspost/rules";
	}
	
	 @RequestMapping("/search")
	 public String search(@Param("keyword") String keyword, Model model) {
	  System.out.println(keyword);
	  model.addAttribute("keyword", keyword);
	  Set<Entry> list=entryRepository.findByKeyword(keyword);
		 System.out.println(list);
		 model.addAttribute("list", list);
	  return "addresspost/search";
	 }
	
	 @RequestMapping(value = "/remove/{email}")
	 @Transactional
		public String remove(@PathVariable String email) {
			entryRepository.deleteByEmail(email);
			return "addresspost/removed";
		}


}
