package com.cts_techacademy.view;

public class ViewGenerator {

	static public void welcomeView() {

		System.out.println("****\t\t\t\t\t\t\t\t\t\t\t");
		System.out.println("****\t\t\t\tWELCOME TO TWEET APP\t\t\t\t\t");
		System.out.println("****\t\t\t\tBY\t\t\t\t\t");
		System.out.println("****\t\t\t\tAKASH KUMAR\t\t\t\t\t");
		System.out.println("****\t\t\t\t\t\t\t\t\t\t\t\t");
		System.out.println("****\t\t\t\tENTER - [1] TO LOGIN\t\t\t\t\t\t");
		System.out.println("****\t\t\t\tENTER - [2] TO REGISTER\t\t\t\t\t");
		System.out.println("****\t\t\t\tENTER - [3] TO FORGOT PASSWORD\t\t\t\t\t");
		System.out.println("****\t\t\t\t\t\t\t\tPRESS [X] TO EXIT APPLICATION");

	}

	static public void loggedInView(String user) {

		System.out.println("****\t\t\t\tWELCOME - \t"+user);
		System.out.println("****\t\t\t\t");
		System.out.println("****\t\t\t\tENTER - [1] POST TWEET");
		System.out.println("****\t\t\t\tENTER - [2] VIEW ALL MY TWEETS");
		System.out.println("****\t\t\t\tENTER - [3] VIEW ALL TWEETS");
		System.out.println("****\t\t\t\tENTER - [4] VIEW ALL USERS");
		System.out.println("****\t\t\t\tENTER - [5] RESET PASSWORD");
		System.out.println("****\t\t\t\tENTER - [6] LOGOUT");
		System.out.println("****\t\t\t\t\t\t\t\tPRESS [X] TO EXIT APPLICATION");


	}

	static public void allUserView(String firstName, String lastName, String userId, String dateOfBirth){
		System.out.format("%-30s%-30s%-40s\n", "****\t\t\t\t"+firstName, lastName, userId);
	}

	static public void tweetView(String tweet,  String lastName, String email){
		System.out.format("%-75s%-15s%-15s\n", "****\t\t\t\t"+tweet,  "by - "+lastName+",",email);
	}

	static public void globalMenuGenerator(String input){

		System.out.println();
		System.out.println("****");
		System.out.print("****\t\t\t\t"+input+"  ");
	}
}