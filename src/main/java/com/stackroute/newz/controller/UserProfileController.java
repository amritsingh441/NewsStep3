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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.newz.model.UserProfile;
import com.stackroute.newz.service.UserProfileService;
import com.stackroute.newz.util.exception.UserProfileAlreadyExistsException;
import com.stackroute.newz.util.exception.UserProfileNotExistsException;


/*
 * As in this assignment, we are working with creating RESTful web service, hence annotate
 * the class with @RestController annotation.A class annotated with @Controller annotation
 * has handler methods which returns a view. However, if we use @ResponseBody annotation along
 * with @Controller annotation, it will return the data directly in a serialized 
 * format. Starting from Spring 4 and above, we can use @RestController annotation which 
 * is equivalent to using @Controller and @ResposeBody annotation
 * 
 * Please note that the default path to use this controller should be "/api/v1/news"
 */

@RestController
public class UserProfileController {

	/*
	 * Autowiring should be implemented for the UserProfileService. Please note that we
	 * should not create any object using the new keyword
	 */
	@Autowired
	UserProfileService profileService;

	/*
	 * Define a handler method which will register a userProfile by reading the Serialized
	 * UserProfile object from request body and save the userProfile in UserProfile table in database.
	 * Please note that the userId has to be unique.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 201(CREATED) - In case of successful creation of the userProfile 
	 * 2. 409(CONFLICT) - In case of duplicate userId
	 * 
	 * This handler method should map to the URL "/api/v1/user" using HTTP POST
	 * method".
	 */
	@PostMapping("/api/v1/user")
	public ResponseEntity<?> addUserProfile(@RequestBody UserProfile profile){
		try {
			UserProfile uProfile = profileService.registerUser(profile);
			if(uProfile!=null)
				return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (UserProfileAlreadyExistsException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
		
	}
	
	

	/*
	 * Define a handler method which will get us all users.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If all news found successfully. 
	 * 
	 * 
	 * 
	 * This handler method should map to the URL "/api/v1/user" using HTTP GET
	 * method.
	 */
	@GetMapping("/api/v1/user")
	public ResponseEntity<?> getAllUserProfiles(){
		profileService.getAllUserProfiles();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	

	/*
	 * Define a handler method which will update a specific userProfile by reading the
	 * Serialized object from request body and save the updated userProfile details in
	 * the userProfile table in database and handle UserProfileNotExistsException as well. 
	 * 
	 * This handler method should return any one of the status
	 * messages basis on different situations: 
	 * 1. 200(OK) - If the userProfile is updated successfully. 
	 * 2. 404(NOT FOUND) - If the userProfile with specified userId is not found. 
	 * 
	 * This handler method should map to the URL "/api/v1/user/{userId}" using HTTP PUT
	 * method, where "userId" should be replaced by a valid userId without {}
	 */
	
	@PutMapping("/api/v1/user/{userId}")
	public ResponseEntity<?> updateUserProfile(@PathVariable String userId){
		
		try {
			UserProfile	uProfile = profileService.getUserProfile(userId);
			profileService.updateUserProfile(uProfile, userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserProfileNotExistsException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	

	/*
	 * Define a handler method which will get us the user by a userId.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the user found successfully. 
	 * 2. 404(NOT FOUND) - If the user with specified userId is not found. 
	 * 
	 * 
	 * This handler method should map to the URL "/api/v1/user/{userId}" using HTTP GET
	 * method, where "userId" should be replaced by a valid userId without {}
	 */
	@GetMapping("/api/v1/user/{userId}")
	public ResponseEntity<?> getUserByUserId(@PathVariable String userId){
		try {
			profileService.getUserProfile(userId);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} catch (UserProfileNotExistsException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	

	/*
	 * Define a handler method which will delete a userProfile from the database.
	 * 
	 * This handler method should return any one of the status messages basis on
	 * different situations: 
	 * 1. 200(OK) - If the userProfile deleted successfully. 
	 * 2.404(NOT FOUND) - If the userProfile with specified userId is not found.
	 * 
	 * This handler method should map to the URL "/api/v1/user/{userId}" using HTTP
	 * Delete method" where "userId" should be replaced by a valid userId without {}
	 */
	
	@DeleteMapping("/api/v1/user/{userId}")
	public ResponseEntity<?> deleteUserProfiles(@PathVariable String userId){
		try {
			profileService.deleteUserProfile(userId);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (UserProfileNotExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
}
