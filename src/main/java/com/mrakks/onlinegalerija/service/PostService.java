package com.mrakks.onlinegalerija.service;

import java.util.Date;
import java.util.List;


import com.mrakks.onlinegalerija.model.Post;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {
	
	List<Post> getAllPosts();
	Post getPostById(Long id);
	void savePostToDB(MultipartFile file, String name, String description, Date date_creation);
}
