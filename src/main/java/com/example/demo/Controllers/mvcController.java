package com.example.demo.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.mvc;
import com.example.demo.Repository.mvcRepo;

import jakarta.servlet.http.HttpSession;

@Controller

public class mvcController {

	@Autowired
	mvcRepo repo;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}
	
	
	@GetMapping("/")
	public String home(Model m) {
		List<mvc> li =(List<mvc>) repo.findAll();
		m.addAttribute("add-products",li);
		return "home";
		
	}
	@GetMapping("/getbyid/{id}")
	public String getbyid(@PathVariable(value = "id") int id,Model m) {
		
		Optional<mvc> c = repo.findById(id);
		mvc v = c.get();
		m.addAttribute("products",v);
		return "edit";
		
	}
	
	@PostMapping("/save_products")
	public String insertion(@ModelAttribute mvc m,HttpSession session) {
		repo.save(m);
		session.setAttribute("message", "successfully inserted");
		return "redirect:/loadform";
	}
	
	@PutMapping("/update")
	public String edit(@ModelAttribute mvc m,HttpSession session) {
		repo.save(m);
		session.setAttribute("message", "successfully updated");
		return "redirect:/";
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable(value = "id") int id ,HttpSession session) {
		repo.deleteById(id);
		session.setAttribute("message", "successfully deleted");
		return "redirect:/";
	}
	
	
	@GetMapping("/loadform")
	public String loadform() {
		return "add";
	}
	
}
