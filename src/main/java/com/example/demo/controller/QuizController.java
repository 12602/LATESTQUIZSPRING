package com.example.demo.controller;

import java.util.ArrayList;
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
@CrossOrigin("*")
public class QuizController {
	     @Autowired
	      QuizService quiz;
	     @Autowired
	       UserService user;
	     @Autowired
	     private ModelMapper modelMapper;

	 @GetMapping("/getQuiz/{cat}")
	 @CrossOrigin("http://localhost:3000")
	 public List<Quiz> getQuiz(@PathVariable("cat")String cat){
		 
		 
		 List<Quiz>li= quiz.getQuiz(cat);
		   Collections.shuffle(li);
		   if(li.size()>=10) {
			   return 	 li.subList(0, 10); 
		   }
		   else
			   return li;
			   
		 
		 
	 }
	 
	 @PostMapping("/addQuiz")
	 @CrossOrigin("http://localhost:3000")
	 public List<Quiz> addQuiz(@RequestBody Map<String, Object> payload) 
	 {
		
		 ArrayList<Object>q=(ArrayList<Object>)payload.get("quiz");
		 ArrayList<Quiz>res=new ArrayList<>();
		  for(Object o:q) {
				Quiz q1= modelMapper.map(o,Quiz.class);
				res.add(q1);
		  }
	
		   
		 
	   //return null;	
	     return   quiz.addQuiz(res);	 
	 }
	 @GetMapping("/allCategories")
	 public List<String	> getCategories(){
		 
		 
		 return quiz.getCat();
		 
	 }
	 @PostMapping("/submitQuiz")
	  public String submitQuiz(@RequestBody Map<String,Object> req) {
		 
		 System.out.println(req	);
		  int userId=Integer.parseInt(req.get("userId").toString());
		  int  score=Integer.parseInt(req.get("score").toString());
		  String quizCat=req.get("category").toString();
		  	
		  
		   
		 return quiz.submitQuiz(userId,score,quizCat);
		 
		 
	 }
	 
	 

}
