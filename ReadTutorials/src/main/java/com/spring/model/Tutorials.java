package com.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tutorials  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String discription;
	private boolean published;
	public Tutorials() {}
	
	public Tutorials(int id, String title, String discription, boolean published) {
		super();
		this.id = id;
		this.title = title;
		this.discription = discription;
		this.published = published;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	@Override
	public String toString() {
		return "Tutorials [id=" + id + ", title=" + title + ", discription=" + discription + ", published=" + published
				+ "]";
	}
	

}
