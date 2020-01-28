package com.stackroute.newz.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/*
 * The class "Reminder" will be acting as the data model for the Reminder Table in the database. 
 * Please note that this class is annotated with @Entity annotation. 
 * Hibernate will scan all package for any Java objects annotated with the @Entity annotation. 
 * If it finds any, then it will begin the process of looking through that particular 
 * Java object to recreate it as a table in your database.
 */

public class Reminder {
	/*
	 * This class should have three fields (reminderId,schedule,news). Out of these
	 * three fields, the field reminderId should be primary key and auto-generated.
	 * This class should also contain the getters and setters for the fields along
	 * with the no-arg , parameterized constructor and toString method. annotate
	 * news field with @OneToOne and add
	 * 
	 * @JsonIgnore annotation.
	 * 
	 * The data type for schedule field should be LocalDateTime. Please
	 * add @JsonSerialize(using = ToStringSerializer.class) for this field
	 */

	public Reminder(int reminderId, LocalDateTime schedule, News news) {

	}

	public Reminder() {

	}

	
	public int getReminderId() {
		return 0;
	}

	
	public void setReminderId(int reminderId) {

	}


	public int getNewsId() {
		return 0;
	}

	public void setNewsId(int newsId) {
		
	}

	
	public LocalDateTime getSchedule() {
		return null;
	}

	
	public void setSchedule(LocalDateTime schedule) {
		
	}

	

}
