package com.mrakks.onlinegalerija.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private String image;
	private Date date_creation;
		
	
	public Post() {
		super();
	}
	
	public Post(String name, String description, String image, Date date_creation) {
		super();
		this.name = name;
		this.description = description;
		this.image = image;
		this.date_creation = date_creation;
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
	public Date getDate_creation() {
		return date_creation;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}
	
	
}
