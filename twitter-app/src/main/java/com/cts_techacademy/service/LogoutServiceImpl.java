package com.cts_techacademy.service;

import com.cts_techacademy.controller.LoginController;
import com.cts_techacademy.dao.CentralTweetDaoImpl;
import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.view.ViewGenerator;
import org.hibernate.Session;

public class LogoutServiceImpl implements LogoutService{

    CentralTweetDaoImpl centralTweetDaoImpl;

    @Override
    public void checkLoginStatusViolation(String username) throws MasterException {
        Boolean isThisActiveLogin = true;
        centralTweetDaoImpl = new CentralTweetDaoImpl();
        try{
            isThisActiveLogin = centralTweetDaoImpl.userLoggedInStatusCheck(username);
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

    @Override
    public void logoutUser(String username) throws MasterException {
        Boolean isThisActiveLogin = true;
        centralTweetDaoImpl = new CentralTweetDaoImpl();
        try{
            centralTweetDaoImpl.setUserInActive(username);
        }
        catch(Exception e){
            throw new MasterException("Issue with Logging out");
        } //throw new MasterException("PLEASE LOG IN AGAIN, YOU ARE LOGGED OUT");
            ViewGenerator.globalMenuGenerator("PLEASE LOG IN AGAIN, YOU ARE LOGGED OUT");
            ViewGenerator.welcomeView();
            LoginController.loginPromt();
    }

    @Override
    public void exitApplication(String choice){

        if(choice.toUpperCase().charAt(0) == "X".charAt(0)){
            System.out.println("FUCK OFF");
            centralTweetDaoImpl = new CentralTweetDaoImpl();
            Session session = centralTweetDaoImpl.getSession();
            centralTweetDaoImpl.killSession(session);
            System.exit(1);
        }
    }
}
