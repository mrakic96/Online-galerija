package com.mrakks.onlinegalerija.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrakks.onlinegalerija.model.Post;
import com.mrakks.onlinegalerija.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public List<Post> getAllPosts() {
		
		return postRepository.findAll();
	}
	
	
}
