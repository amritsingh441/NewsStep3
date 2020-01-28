package com.stackroute.newz.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;


/*
 * The class "News" will be acting as the data model for the News Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

public class News {

	/*
	 * This class should have ten fields
	 * (newsId,title,author,description, publishedAt, content, url, urlToImage,user,reminder). 
	 * Out of these ten fields, the
	 * field newsId should be primary key and auto-generated. This class should
	 * also contain the getters and setters for the fields along with the no-arg ,
	 * parameterized constructor and toString method. 
	 * annotate user field with @ManyToOne and reminder field as @OneToOne and add
	 * @JsonIgnore for both of them.
	 * 
	 * The data type for publishedAt field should be LocalDateTime. 
	 * Please add @JsonSerialize(using = ToStringSerializer.class) for this field
	 */
	
	
	
	
	public News(int newsId, String title, String author, String description, LocalDateTime publishedAt, String content,
			String url, String urlToImage, UserProfile user, Reminder reminder) {
		
		
	}
	
	
	/**
	 * @return the newsID
	 */
	
	public News() {
		
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return null;
	}
	/**
	 * @return the newsID
	 */
	public int getNewsId() {
		return 0;
		
	}
	/**
	 * @param newsID the newsID to set
	 */
	public void setNewsId(int newsId) {
		
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return null;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		
	}
	/**
	 * @return the urlToImage
	 */
	public String getUrlToImage() {
		return null;
	}
	/**
	 * @param urlToImage the urlToImage to set
	 */
	public void setUrlToImage(String urlToImage) {
		
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return null;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return null;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		
	}
	/**
	 * @return the publishedAt
	 */
	public LocalDateTime getPublishedAt() {
		return null;
	}
	/**
	 * @param publishedAt the publishedAt to set
	 */
	public void setPublishedAt(LocalDateTime publishedAt) {
		
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return null;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		
	}
	

	
	
}
