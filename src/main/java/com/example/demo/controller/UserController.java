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
@CrossOrigin({"http://localhost:3000","https://quizappspringreact.netlify.app/","https://quizappbackend-production-af37.up.railway.app/"})
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService user;
   @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User u) {
	   
	    
	     User newuser= user.loginUser(u.email,u.password);
	     System.out.println(newuser);	
	      return ResponseEntity.ok(newuser);
	   
	   
   }
	@PostMapping("/getUser")
   public ResponseEntity<Integer> getUser(@RequestBody User u) {
	   
	    
	     User newuser= user.loginUser(u.email,u.password);
	     System.out.println(newuser.getUserId());
	     int userId=newuser.getUserId();
	     System.out.println(userId);
	      return ResponseEntity.ok(userId);
	   
	   
  }
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
   @GetMapping("/results/{id}")
   public ResponseEntity<?> quizResults(@PathVariable("id")int id){
	        try 
	        {
	        	 System.out.println("userId:"+id);
	        	 List<UserQuiz> u= user.getResults(id);
                 return ResponseEntity.ok(u);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Errror"+e);
				return null;
			}
	      }
   @GetMapping("/allUsers")
    public ResponseEntity<?> allUsers(){
	  List<User>li=user.allUsers();
	   return ResponseEntity.ok(li); 
   }
   @PostMapping("/admin")
    public ResponseEntity<?> addAdmin(@RequestBody User u) {
	   
	    User us=user.insertAdmin(u);	
	    return ResponseEntity.ok(us);
   }
}
