package com.mrakks.onlinegalerija.service;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import com.mrakks.onlinegalerija.model.User;
import com.mrakks.onlinegalerija.repository.UserRepository;
import com.mrakks.onlinegalerija.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.mrakks.onlinegalerija.model.Post;
import com.mrakks.onlinegalerija.repository.PostRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Post> getAllPosts() {
		
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(Long id) {
		return postRepository.findById(id).get();
	}

	private String convertToString(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")){
			System.out.println("Not a valid file");
		}
		try {
			return Base64.getEncoder().encodeToString(file.getBytes());
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void save(MultipartFile file, String name, String description, User user, Date createdAt) {
		Post post = new Post();
		post.setImage(convertToString(file));
		post.setName(name);
		post.setDescription(description);
		post.setUser(user);
		post.setCreatedAt(createdAt);
		user.addNewPost(post);
		userRepository.save(user);
//		postRepository.save(post);
	}

	@Override
	public void update(long postId, MultipartFile file, String name, String description) {

		Post post = postRepository.findById(postId).get();
		post.setName(name);
		post.setDescription(description);
		post.setImage(convertToString(file));

		postRepository.save(post);
	}

	@Override
	public void delete(User user, Post post) {
		user.deleteAPost(post);
		userRepository.save(user);
	}
}
