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
	
	@GetMapping("/addPost")
	public String showAddPost() {
		
		return "addPost";
	}
	
	@PostMapping("/addP")
	public String savePost(@RequestParam("name") String name, 
							@RequestParam("description") String description, 
							@RequestParam("image") String image) {
		Post post = new Post();
		post.setName(name);
		post.setDescription(description);
		post.setImage(image);
		post.setDate_creation(new Date());
		postRepository.save(post);
		return "redirect:/";
	}

	@GetMapping("/editPost/{id}")
	public String editPost (@PathVariable("id") Long id, Model model){
		model.addAttribute("post", postService.getPostById(id));
		return "editPost";
	}

	@PostMapping("/updatePost")
	public String updatePost(@RequestParam("id") Long id,
							 @RequestParam("name") String name,
							 @RequestParam("description") String description,
							 @RequestParam("image") String image
	){
		Post post = postRepository.findById(id).get();
		post.setName(name);
		post.setDescription(description);
		post.setImage(image);
		postRepository.save(post);
		return "redirect:/";
	}

	@GetMapping("/deletePost/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		
		postRepository.deleteById(id);
		return "redirect:/";
	}
	
}
	
