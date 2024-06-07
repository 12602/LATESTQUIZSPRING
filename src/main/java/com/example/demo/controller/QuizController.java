package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Quiz;
import com.example.demo.entity.User;
import com.example.demo.service.QuizService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/quiz")
@CrossOrigin({"http://localhost:3000","https://quizappspringreact.netlify.app/","https://quizappbackend-production-af37.up.railway.app/"})
public class QuizController {
	     @Autowired
	      QuizService quiz;
	     @Autowired
	       UserService user;
	     @Autowired
	     private ModelMapper modelMapper;

	 @GetMapping("/getQuiz/{cat}")

	 public List<Quiz> getQuiz(@PathVariable("cat")String cat){
		    
		 
		 List<Quiz>li= quiz.getQuiz(cat);
		   Collections.shuffle(li);
		   System.out.println(cat);
		   if(li.size()>=10) {
			   return 	 li.subList(0, 10); 
		   }
		   else
			   return li;
			   
		 
		 
	 }
	 @GetMapping("/getCompleteQuiz/{cat}")
	
	 public List<Quiz> getCompleteQuiz(@PathVariable("cat")String cat){
		    
		 
		 List<Quiz>li= quiz.getQuiz(cat);
		 
		 
			   return li;
			   
		 
		 
	 }
	 
	 @PostMapping("/addQuiz")
	 
	 public List<Quiz> addQuiz(@RequestBody Map<String, Object> payload) 
	 {
		  System.out.println(payload);
		 ArrayList<Object>q=(ArrayList<Object>)payload.get("quiz");
		 ArrayList<Quiz>res=new ArrayList<>();
		  for(Object o:q) {
				Quiz q1= modelMapper.map(o,Quiz.class);
				res.add(q1);
		  }
	
		   
		 
	   //return null;	
	     return   quiz.addQuiz(res);	 
	 }
	 @PostMapping("/addQuizJson")
	 public List<Quiz>addQuizJson(@RequestBody ArrayList<Quiz>q){
	      
		 return quiz.addQuiz(q);
		// return quiz.addQuiz(al);
	 }
	 @GetMapping("/allCategories")
	 public List<Quiz> getCategories(){
		 
		 
		 List<Quiz>r= quiz.getCat();
		   System.out.print(r);
		   return r;	
	 }
	 @PostMapping("/submitQuiz")
	  public String submitQuiz(@RequestBody Map<String,Object> req) {
		   try {
			   System.out.println(req	);
				 int userId=Integer.parseInt(req.get("userId").toString());
				  int  score=Integer.parseInt(req.get("score").toString());
				  String quizCat=req.get("category").toString();
				  	
				  
				    
				 String res= quiz.submitQuiz(userId,score,quizCat);
				 System.out.println(res);
				 return res;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in uploading quiz");
			return "Error in submitting quiz";
		}
		
		 
	 }
	 @GetMapping("/allQuestions")
	 public List<Quiz> getAllQuestions(){
		    return quiz.getAllQuiz();
	 }
	 
	 

}
