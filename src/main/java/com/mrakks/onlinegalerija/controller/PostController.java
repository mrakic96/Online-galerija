package com.mrakks.onlinegalerija.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mrakks.onlinegalerija.service.PostService;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//	display list of posts
	@GetMapping("/")
	public String viewHomePage (Model model) {
		model.addAttribute("listPosts", postService.getAllPosts());
		return "index";
	}
}
