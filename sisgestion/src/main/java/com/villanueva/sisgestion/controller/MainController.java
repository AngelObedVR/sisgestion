package com.villanueva.sisgestion.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainController {
	@GetMapping("/infracciones")
	public String getCategories(Model model) {
		return "infracciones";
	}
}
