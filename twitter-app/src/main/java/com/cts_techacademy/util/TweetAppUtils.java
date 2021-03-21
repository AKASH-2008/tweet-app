package com.cts_techacademy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.view.ViewGenerator;

public class TweetAppUtils {

	public static void clearScreen() {  
		final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
	} 

	public static String reader(String what) {

		Scanner sc= new Scanner(System.in); //System.in is a standard input stream
		what = "Enter a "+what+": ";
		ViewGenerator.globalMenuGenerator(what);
		String str= sc.nextLine();
		return str;
	}
	public static String dateReader(String what) {

		Scanner sc= new Scanner(System.in); //System.in is a standard input stream
		what = "Enter a "+what+": ";
		ViewGenerator.globalMenuGenerator(what);
		ViewGenerator.globalMenuGenerator("ENTER DAY IN -- DD");
		String day= sc.nextLine();
		ViewGenerator.globalMenuGenerator("ENTER MONTH IN -- MM");
		String month= sc.nextLine();
		ViewGenerator.globalMenuGenerator("ENTER YEAR IN -- YYYY");
		String year= sc.nextLine();
		return day+"-"+month+"-"+year;
	}

	public static void validateChoice(int choice) throws MasterException{
		if(choice != 1 && choice != 2 && choice !=3 ) {
			throw new MasterException("not valid, Enter either 1 or 2");
		}
	}

	public static void validateTweetMenuChoice(int choice) throws MasterException{
		if(choice != 1 && choice != 2 && choice !=4 && choice !=5 && choice !=3 && choice !=6) {
			throw new MasterException("not valid choice");
		}
	}


	public static Boolean validateEmail(String email) {
		final Pattern VALID_EMAIL_ADDRESS_REGEX =
				Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		return matcher.find();
	}

	public static void sleepCode(long sleepSeconds) throws InterruptedException {
		Thread.sleep(sleepSeconds * 1000);
	}

	public static Boolean validateDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		df.setLenient(false);
		try {
			df.parse(date);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public static String encryptPassWordSimple(String password){
		//Not Required in Business Requirement
		return null;
	}
	public static String decryptPassWordSimple(String password){
		//Not Required in Business Requirement
		return null;
	}

}
