package com.mrakks.onlinegalerija.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	// show add a new post view
	@GetMapping("/addPost")
	public String showAddPost() {
		
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
							 @RequestParam("image") MultipartFile image){
//		get? -> exception handler -- orElseThrow()
		Post post = postRepository.findById(id).get();
		postService.update(post.getId(), image, name, description);
		return "redirect:/";
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
	
}
	
