package com.cts_techacademy.controller;


import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.service.LoginService;
import com.cts_techacademy.service.LogoutService;
import com.cts_techacademy.util.TweetAppUtils;
import com.cts_techacademy.view.ViewGenerator;

public class LoginController {

	public LoginController() throws InterruptedException {
		super();
		ViewGenerator.welcomeView();
	}
	
	public static void loginPromt() throws MasterException{
		
		String str = TweetAppUtils.reader("Choice");
		LogoutService logoutService = new LogoutService();
		logoutService.exitApplication(str);
			LoginService service = new LoginService();
			try {
				service.choice(str);
			} catch (MasterException e) {
				throw new MasterException("LOGIN CONTROLLER ISSUE");
			}
	}

	public static void loggedIn(String user) throws MasterException {
		TweetAppController tweetAppController = new TweetAppController(user);
	}

	
}
