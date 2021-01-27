package com.mrakks.onlinegalerija.service;

import java.util.Date;
import java.util.List;


import com.mrakks.onlinegalerija.model.Post;
import com.mrakks.onlinegalerija.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
	
	List<Post> getAllPosts();
	Post getPostById(Long id);
	void save(MultipartFile file, String name, String description, User user, Date date_creation);
	void update(long postId, MultipartFile file, String name, String description);
	void delete(User user, Post post);

}
