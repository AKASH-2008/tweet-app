package com.cts_techacademy.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "users_id")
	private long userId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private String status;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="userAssocId", nullable=false)
	private List<Tweets> tweets;

	public Users() {
		super();
	}

	@Override
	public String toString() {
		return "Users{" +
				"userId=" + userId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dateOfBirth='" + dateOfBirth + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", status='" + status + '\'' +
				", tweets=" + tweets +
				'}';
	}

	public Users(String firstName, String lastName, String dateOfBirth, String email, String password, String status, List<Tweets> tweets) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
		this.status = status;
		this.tweets = tweets;
	}

	public List<Tweets> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweets> tweets) {
		this.tweets = tweets;
	}

	public Users(String firstName, String lastName, String dateOfBirth, String email, String password, String status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.password = password;
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
