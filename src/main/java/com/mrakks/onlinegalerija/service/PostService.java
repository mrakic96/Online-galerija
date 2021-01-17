package com.mrakks.onlinegalerija.service;

import java.util.List;


import com.mrakks.onlinegalerija.model.Post;

public interface PostService {
	
	List<Post> getAllPosts();
	Post getPostById(Long id);

}
