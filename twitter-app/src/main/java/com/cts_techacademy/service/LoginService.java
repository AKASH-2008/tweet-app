package com.cts_techacademy.service;

import com.cts_techacademy.controller.LoginController;
import com.cts_techacademy.dao.CentralTweetDao;
import com.cts_techacademy.entity.Users;
import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.util.TweetAppUtils;
import com.cts_techacademy.view.ViewGenerator;

public class LoginService {

	CentralTweetDao centralTweetDao;

	public void choice(String loginChoice) throws MasterException, NumberFormatException{

		try{
			int choice = Integer.parseInt(loginChoice);
				TweetAppUtils.validateChoice(choice);
			     if (choice == 1) {
			    	 loginToTwitter();
			     }
			     else if(choice == 2){
			    	 registerToTwitter();
			     }
			     else if(choice == 3){
			    	 forgotPassword();
			     }
		      }catch(Exception m){

			loginReiteration();
		    	  throw new MasterException("not valid, Enter either 1, 2 or 3");
		      }
		
	}

	private void forgotPassword() throws MasterException, InterruptedException {

		centralTweetDao = new CentralTweetDao();
		boolean canUserChangePassword = false;
		ViewGenerator.globalMenuGenerator("ENTER YOUR EMAIL TO RECOVER");
		String email = TweetAppUtils.reader("email");
		ViewGenerator.globalMenuGenerator("ENTER YOUR EMAIL TO DATE OF BIRTH");
		String dateOfBirth = TweetAppUtils.dateReader("date of birth");
		canUserChangePassword = centralTweetDao.forgotPasswordCheck(email, dateOfBirth);
		if(!canUserChangePassword){
			TweetAppUtils.sleepCode(2);
			ViewGenerator.globalMenuGenerator("YOU HAVE ENTERED WRONG EMAIL OR DOB");
			loginReiteration();
		}
		else{
			ViewGenerator.globalMenuGenerator("ENTER NEW PASSWORD FOR YOUR ACCOUNT");
			String forgotPassword = TweetAppUtils.reader("new Password");
			centralTweetDao.resetPassword(email, forgotPassword);
			TweetAppUtils.sleepCode(2);
			loginReiteration();
		}
	}

	private void loginToTwitter() throws MasterException{
		Boolean validUser = false;
		String username = null;
		String password = null;
		centralTweetDao = new CentralTweetDao();
		int count = 0;
		try{
		while(!validUser){
			ViewGenerator.globalMenuGenerator("ENTER VALID CREDENTIALS");
			username = TweetAppUtils.reader("USERID / EMAIL");
			password = TweetAppUtils.reader("PASSWORD");
			validUser = centralTweetDao.userLoginCheck(username, password);
			if(count == 2){
				ViewGenerator.globalMenuGenerator("LIMIT EXCEEDED, TRY AGAIN");
				ViewGenerator.globalMenuGenerator("CREATE NEW ACCOUNT OR TRY TO RESET PASSWORD");
				ViewGenerator.welcomeView();
				LoginController.loginPromt();}
			count++;
		}
		}catch(MasterException m){
			loginReiteration();
			throw new MasterException("not valid, Login Issue");
		}
		centralTweetDao.setUserActive(username);
		//centralTweetDao.killSession(HibernateUtil.buildSessionFactory().openSession());
		LoginController.loggedIn(username);
	}

	private void registerToTwitter() throws MasterException, InterruptedException {
		centralTweetDao = new CentralTweetDao();
		try{
		boolean validateEmail = false;
		boolean validateDate = false;
		String email = null;
		String dateOfBirth = null;
		ViewGenerator.globalMenuGenerator("REGISTER TO TWEET APP");
		String firstName = TweetAppUtils.reader("FIRST NAME");
		String lastName = TweetAppUtils.reader("LAST NAME");

		dateOfBirth = TweetAppUtils.dateReader("DATE OF BIRTH");
		validateDate = TweetAppUtils.validateDate(dateOfBirth);

		while(!validateDate){
			dateOfBirth = TweetAppUtils.dateReader("VALID DATE OF BIRTH");
			validateDate = TweetAppUtils.validateDate(dateOfBirth);
		}

		email = TweetAppUtils.reader("EMAIL");
		validateEmail = TweetAppUtils.validateEmail(email);

		while(!validateEmail){
			email = TweetAppUtils.reader("VALID EMAIL - IT WILL BE USED AS USERNAME");
			validateEmail = TweetAppUtils.validateEmail(email);
		}

		String password = TweetAppUtils.reader("PASSWORD");
		Users user = new Users();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDateOfBirth(dateOfBirth);
		user.setEmail(email);
		user.setPassword(password);
		ViewGenerator.globalMenuGenerator(user.toString());
			centralTweetDao.saveUser(user);
			ViewGenerator.globalMenuGenerator("USER HAS BEEN REGISTERED - PLEASE LOGIN TO USE TWEET APP");
			ViewGenerator.globalMenuGenerator(" ");
			TweetAppUtils.sleepCode(2l);
		}
		catch (Exception e){
			ViewGenerator.globalMenuGenerator("UNABLE TO REGISTER THE USER - USER WITH EMAIL ALREADY EXISTS");
			TweetAppUtils.sleepCode(2);
			throw new MasterException("UNABLE TO REGISTER THE USER - USER WITH EMAIL ALREADY EXISTS");
		}finally {
			loginReiteration();
		}
		LoginController.loginPromt();
	}
	 private void loginReiteration() throws MasterException {
		 ViewGenerator.welcomeView();
		 LoginController.loginPromt();
	 }
}
