package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Quiz;
import com.example.demo.entity.User;
import com.example.demo.entity.UserQuiz;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin({"http://localhost:3000","https://quizappsrpring.netlify.app/","https://quizappbackend-production-af37.up.railway.app/"})
@RequestMapping("/user")
	@CrossOrigin("*")
public class UserController {
	@Autowired
	UserService user;
	@CrossOrigin("http://localhost:3000")
   @PostMapping("/login")
    public User login(@RequestBody User u) {
	   
	    
	     return user.loginUser(u.email,u.password);
	     
	   
	   
   }
   @CrossOrigin("http://localhost:3000")
   @PostMapping("/register")
   public ResponseEntity<?> register(@RequestBody User u) 
   {
	   
	   
	     User newuser= user.createUser(u);
	     System.out.println(newuser);
	     if(newuser==null)
	     {
	    	return ResponseEntity.ok("User Already exist !!Please change Username or Email");
	     }
	      
	     return ResponseEntity.ok(newuser);
   }
	@CrossOrigin("http://localhost:3000")
   @GetMapping("/results/{id}")
   public List<UserQuiz> quizResults(@PathVariable("id")int id){
	        try 
	        {
	        	 System.out.println("userId:"+id);
	        	 return user.getResults(id);

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Errror"+e);
				return null;
			}
	      }
	@CrossOrigin("http://localhost:3000")
   @GetMapping("/allUsers")
    public List<User> allUsers(){
	   return user.allUsers();  
   }
	@CrossOrigin("http://localhost:3000")
   @PostMapping("/admin")
    public User addAdmin(@RequestBody User u) {
	   
	    return user.insertAdmin(u);	
	   
   }
}
