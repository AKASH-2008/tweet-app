package com.cts_techacademy.service;

import com.cts_techacademy.controller.LoginController;
import com.cts_techacademy.dao.CentralTweetDao;
import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.view.ViewGenerator;
import org.hibernate.Session;

import java.util.Locale;

public class LogoutService {

    CentralTweetDao centralTweetDao;

    public void checkLoginStatusViolation(String username) throws MasterException {
        Boolean isThisActiveLogin = true;
        centralTweetDao = new CentralTweetDao();
        try{
            isThisActiveLogin = centralTweetDao.userLoggedInStatusCheck(username);
        }
        catch(Exception e){
            throw new MasterException("The User have bee Logged Out");
        }
        if(!isThisActiveLogin){
            //throw new MasterException("PLEASE LOG IN AGAIN, YOU ARE LOGGED OUT");
            ViewGenerator.globalMenuGenerator("PLEASE LOG IN AGAIN, YOU ARE LOGGED OUT");
            ViewGenerator.welcomeView();
            LoginController.loginPromt();
        }
    }

    public void logoutUser(String username) throws MasterException {
        Boolean isThisActiveLogin = true;
        centralTweetDao = new CentralTweetDao();
        try{
            centralTweetDao.setUserInActive(username);
        }
        catch(Exception e){
            throw new MasterException("Issue with Logging out");
        } //throw new MasterException("PLEASE LOG IN AGAIN, YOU ARE LOGGED OUT");
            ViewGenerator.globalMenuGenerator("PLEASE LOG IN AGAIN, YOU ARE LOGGED OUT");
            ViewGenerator.welcomeView();
            LoginController.loginPromt();
    }

    public void exitApplication(String choice){

        if(choice.toUpperCase().charAt(0) == "X".charAt(0)){
            System.out.println("FUCK OFF");
            centralTweetDao = new CentralTweetDao();
            Session session = centralTweetDao.getSession();
            centralTweetDao.killSession(session);
            System.exit(1);
        }
    }
}
