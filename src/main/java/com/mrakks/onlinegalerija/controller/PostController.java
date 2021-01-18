package com.mrakks.onlinegalerija.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mrakks.onlinegalerija.model.Post;
import com.mrakks.onlinegalerija.repository.PostRepository;
import com.mrakks.onlinegalerija.service.PostService;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepository;
	
	//	display list of posts
	@GetMapping("/")
	public String viewHomePage (Model model) {
		model.addAttribute("listPosts", postService.getAllPosts());
		return "index";
	}

	// show add a new post view
	@GetMapping("/addPost")
	public String showAddPost() {
		
		return "addPost";
	}

	// save a new post to DB
	@PostMapping("/savePost")
	public String savePost(@RequestParam("name") String name, 
							@RequestParam("description") String description, 
							@RequestParam("image") MultipartFile image) {
		postService.savePostToDB(image, name, description, new Date());
		return "redirect:/";
	}

	// show edit post view
	@GetMapping("/editPost/{id}")
	public String editPost (@PathVariable("id") Long id, Model model){
		model.addAttribute("post", postService.getPostById(id));
		return "editPost";
	}

	// update a post in DB
	@PostMapping("/updatePost")
	public String updatePost(@RequestParam("id") Long id,
							 @RequestParam("name") String name,
							 @RequestParam("description") String description,
							 @RequestParam("image") MultipartFile image
	){
		Post post = postRepository.findById(id).get();
		postService.savePostToDB(image, name, description, new Date());
		postRepository.save(post);
		return "redirect:/";
	}

	// remove a post from DB
	@GetMapping("/deletePost/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		
		postRepository.deleteById(id);
		return "redirect:/";
	}
	
}
	
