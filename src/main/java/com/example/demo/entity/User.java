package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor	
@Getter
@Setter
public class User 
{
	public User() {
		
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", isAdmin=" + isAdmin + ", allQuiz=" + allQuiz + "]";
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {	
		this.isAdmin = isAdmin;
	}
	public List<UserQuiz> getAllQuiz() {
		return allQuiz;
	}
	public void setAllQuiz(List<UserQuiz> allQuiz) {
		this.allQuiz = allQuiz;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  public int userId;
	 @Column(unique = true)
 public String userName;
 public String password;
 @Column(unique = true)
 public String email;
 public boolean isAdmin;
 
  @OneToMany(cascade = CascadeType.ALL)
 public List<UserQuiz> allQuiz=new ArrayList<>();
}
