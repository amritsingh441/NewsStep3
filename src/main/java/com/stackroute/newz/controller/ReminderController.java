package com.stackroute.newz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.News;
import com.stackroute.newz.model.Reminder;
import com.stackroute.newz.service.NewsService;
import com.stackroute.newz.service.ReminderService;
import com.stackroute.newz.util.exception.NewsAlreadyExistsException;
import com.stackroute.newz.util.exception.NewsNotExistsException;
import com.stackroute.newz.util.exception.ReminderNotExistsException;

/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 * 
 * Please note that the default path to use this controller should be "/api/v1/reminder"
 */
@RestController
public class ReminderController {

	/*
	 * Autowiring should be implemented for the ReminderService. Please note that we
	 * should not create any object using the new keyword
	 */
	@Autowired
	ReminderService remService;


	/*
	 * Define a handler method which will get us all reminders.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If all reminders found successfully. 
	 * 
	 * This handler method should map to the URL "/api/v1/reminder" using HTTP GET
	 * method.
	 */
	
	@GetMapping("/api/v1/reminder")
	public ResponseEntity<List<Reminder>> getAllReminders (){
		remService.getAllReminders();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

	/*
	 * Define a handler method which will get us the reminder by a reminderId.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the reminder found successfully. 
	 * 2. 404(NOT FOUND) - If the reminder with specified reminderId is not found. 
	 * 
	 * 
	 * This handler method should map to the URL "/api/v1/reminder/{reminderId}" using HTTP GET
	 * method, where "reminderId" should be replaced by a valid reminderId without {}
	 */
	
	@GetMapping("/api/v1/reminder/{reminderId}")
	public ResponseEntity<Reminder> getRemindersById (@PathVariable("reminderId") int reminderId){

		try {
			Reminder remObj = remService.getReminder(reminderId);
			if(remObj!=null) {
				return new ResponseEntity<>(remObj,HttpStatus.OK);
			}
		} catch (ReminderNotExistsException e) {
			e.getMessage();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	

	/*
	 * Define a handler method which will create a reminder by reading the Serialized
	 * reminder object from request body and save the reminder in reminder table in database.
	 * Please note that the reminderId has to be unique.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 201(CREATED - In case of successful creation of the reminder 
	 * 2. 409(CONFLICT) - In case of duplicate reminderId
	 * 
	 * This handler method should map to the URL "/api/v1/reminder" using HTTP POST
	 * method".
	 */
	@PostMapping("/api/v1/reminder")
	public ResponseEntity<News> addReminders(@RequestBody Reminder reminder ){
		try {
			Reminder remObj = remService.getReminder(reminder.getReminderId());
			if(remObj!=null) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		} catch (ReminderNotExistsException e) {
			e.printStackTrace();
		}
		remService.addReminder(reminder);
		return new ResponseEntity<>(HttpStatus.CREATED);	
	}
	
	
	

	/*
	 * Define a handler method which will update a specific reminder by reading the
	 * Serialized object from request body and save the updated reminder details in
	 * the reminder table in database and handle ReminderNotExistsException as well. 
	 * 
	 * This handler method should return any one of the status
	 * messages basis on different situations: 
	 * 1. 200(OK) - If the reminder is updated successfully. 
	 * 2. 404(NOT FOUND) - If the reminder with specified reminderId is not found. 
	 * 
	 * This handler method should map to the URL "/api/v1/reminder/{reminderId}" using HTTP PUT
	 * method, where "reminderId" should be replaced by a valid reminderId without {}
	 */
	@PutMapping("/api/v1/reminder/{reminderId}")
	public ResponseEntity<?> updateReminders(@PathVariable int reminderId ){
		try {
			Reminder remUpdated = remService.updateReminder(remService.getReminder(reminderId));
			if(remUpdated!=null)
				return new ResponseEntity<>(HttpStatus.OK);
		} catch (ReminderNotExistsException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	
	
	

	/*
	 * Define a handler method which will delete a reminder from the database.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the reminder deleted successfully. 
	 * 2.404(NOT FOUND) - If the reminder with specified reminderId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/reminder/{reminderId}" using HTTP
	 * Delete method" where "reminderId" should be replaced by a valid reminderId without {}
	 */
	@DeleteMapping("/api/v1/reminder/{reminderId}")
	public ResponseEntity<?> deleteReminders(@PathVariable int reminderId ){
		try {
			remService.deleteReminder(reminderId);
				return new ResponseEntity<>(HttpStatus.OK);
		} catch (ReminderNotExistsException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	
	

}
