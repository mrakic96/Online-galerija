package com.mrakks.onlinegalerija.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mrakks.onlinegalerija.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
