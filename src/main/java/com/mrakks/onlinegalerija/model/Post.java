package com.mrakks.onlinegalerija.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	private String description;

	@Lob
	@Column(columnDefinition = "MEDUIMBLOB")
	private String image;

	@Column(name = "created_at")
	private Date createdAt;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Post() {
		super();
	}
	
	public Post(String name, String description, String image, User user, Date date_creation) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.user = user;
		this.createdAt = date_creation;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCreatedAt(Date date_creation) {
		this.createdAt = date_creation;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

//	public void setUserId(long id) {
//		??
//	}

	
}
