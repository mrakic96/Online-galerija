package com.mrakks.onlinegalerija;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mrakks.onlinegalerija.model.Post;
import com.mrakks.onlinegalerija.repository.PostRepository;

@SpringBootTest
class OnlineGalerijaApplicationTests {
	
	@Autowired
	private PostRepository postRepository;


}
