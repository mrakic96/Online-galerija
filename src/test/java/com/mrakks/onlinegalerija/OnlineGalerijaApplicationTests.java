package com.mrakks.onlinegalerija;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.mrakks.onlinegalerija.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mrakks.onlinegalerija.model.Post;
import com.mrakks.onlinegalerija.repository.PostRepository;

@SpringBootTest
class OnlineGalerijaApplicationTests {
	
	@Autowired
	private PostRepository postRepository;

	@Test
	public void testShouldReturnAPost (){
		User user1 = new User("Matej", "password123", "ADMIN", "ADD_POST");
		User user2 = new User("Nikola", "password123", "ADMIN", "ADD_POST");
		Post post1 = new Post("naziv", "opis", "slika", user1, new Date());
		Post post2 = new Post("naziv2", "opis2", "slika2", user1, new Date());
		Set<Post> posts = new HashSet<>();
		posts.add(post1);
		posts.add(post2);

		Post post = user1.getPost(post1, posts);
		user1.getPosts().remove(post);
	}


}
