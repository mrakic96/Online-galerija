package com.mrakks.onlinegalerija.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mrakks.onlinegalerija.model.Post;
import com.mrakks.onlinegalerija.repository.PostRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	@Override
	public List<Post> getAllPosts() {
		
		return postRepository.findAll();
	}

	@Override
	public Post getPostById(Long id) {
		return postRepository.findById(id).get();
	}

	@Override
	public void savePostToDB(MultipartFile file, String name, String description, Date date_creation) {
		Post post = new Post();
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")){
			System.out.println("Not a valid file");
		}
		try {
			post.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		post.setName(name);
		post.setDescription(description);
		post.setDate_creation(date_creation);

		postRepository.save(post);
	}


}
