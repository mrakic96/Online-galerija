package com.mrakks.onlinegalerija.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import com.mrakks.onlinegalerija.model.User;
import com.mrakks.onlinegalerija.repository.UserRepository;
import com.mrakks.onlinegalerija.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.mrakks.onlinegalerija.model.Post;
import com.mrakks.onlinegalerija.repository.PostRepository;
import com.mrakks.onlinegalerija.service.PostService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	//	display list of posts
	@GetMapping("/hometestt")
	public String viewHomePage (Model model) {
		model.addAttribute("listPosts", postService.getAllPosts());
		return "hometestt";
	}

	//Display a single post
	@GetMapping("/post/{id}")
	public String post (Model model, @PathVariable("id") Long id, Authentication auth) {

		// User currently logged in
		if (auth != null) {
			UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
			User user = userPrincipal.getUser();
//                String username = user.getUsername();
			model.addAttribute("user", user);
		} else {
			model.addAttribute("user", new User());
		}
		//find post by Id
		model.addAttribute("post", postService.getPostById(id));
		return "post";
	}

	// show add a new post view
	@GetMapping("/addPost")
	public String showAddPost(Model model, Authentication auth) {
		// User currently logged in
		if (auth != null) {
			UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
			User user = userPrincipal.getUser();
//                String username = user.getUsername();
			model.addAttribute("user", user);
		} else {
			model.addAttribute("user", new User());
		}
		return "addPost";
	}

	// save a new post to DB
	@PostMapping("/savePost")
	public String savePost(@RequestParam("name") String name,
						   @RequestParam("description") String description,
						   @RequestParam("image") MultipartFile image,
						   Authentication auth
						   ) {
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		User user = userPrincipal.getUser();
		postService.save(image, name, description, user, new Date());
		return "redirect:/home";
	}

	// show edit post view
	@GetMapping("/editPost/{id}")
	public String editPost (@PathVariable("id") Long id, Model model, Authentication auth){

		// Get logged in user
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
		User user = userPrincipal.getUser();

		model.addAttribute("user", user);
		model.addAttribute("post", postService.getPostById(id));
		return "editPost";
	}

	// update a post in DB
	@PostMapping("/updatePost")
	public String updatePost(@RequestParam("id") Long id,
							 @RequestParam("name") String name,
							 @RequestParam("description") String description,
							 @RequestParam("image") MultipartFile image){
//		get? -> exception handler -- orElseThrow()
		Post post = postRepository.findById(id).get();
		postService.update(post.getId(), image, name, description);
		return "redirect:/home";
	}

	// remove a post from DB
	@GetMapping("/deletePost/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		
		postRepository.deleteById(id);
		return "redirect:/";
	}

	@GetMapping("/profile/{id}")
	public String profile(Model model, @PathVariable("id") Long userId) {

		model.addAttribute("user", userRepository.findById(userId).get());
		return "/profile";
	}

	@GetMapping("/{username}/posts")
	public String profile(Model model, @PathVariable("username") String username, Authentication auth) {

		// User currently logged in
		if (auth != null) {
			UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
			User user = userPrincipal.getUser();
//                String username = user.getUsername();
			model.addAttribute("loggeduser", user);
		} else {
			model.addAttribute("loggeduser", new User());
		}

		model.addAttribute("user", userRepository.findByUsername(username));
		return "/posts";
	}
	
}
	
