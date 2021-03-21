package com.cts_techacademy.service;

import com.cts_techacademy.controller.TweetAppController;
import com.cts_techacademy.dao.CentralTweetDaoImpl;
import com.cts_techacademy.entity.Tweets;
import com.cts_techacademy.entity.Users;
import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.util.TweetAppUtils;
import com.cts_techacademy.view.ViewGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class TweetAppServiceImpl implements TweetAppService{

    CentralTweetDaoImpl centralTweetDaoImpl = null;
    LogoutServiceImpl logoutServiceImpl = null;

    public void choice(String loginChoice, String username) throws MasterException, NumberFormatException{
        try{
            int choice = Integer.parseInt(loginChoice);
            TweetAppUtils.validateTweetMenuChoice(choice);
            if (choice == 1) {
                postTweet(choice, username);
                //post tweet
            }
            else if(choice == 2){
                viewAllMyTweets(choice, username);//COMPLETED
            }

            else if(choice == 3){
                viewAllTweets(choice, username);//COMPLETED
            }

            else if(choice == 4){
                System.out.println("ENTERED");
                viewAllUsers(choice, username);//COMPLETED
            }
            
            else if(choice == 5){
                resetPassword(choice, username);
            }
            else if(choice == 6){
                logout(choice, username);//COMPLETED
            }
        }catch(Exception m){
            reiterativeMenu(username);
            throw new MasterException("not valid choice");
        }

    }

    private void resetPassword(int choice, String username) throws MasterException {
        logoutServiceImpl = new LogoutServiceImpl();
        logoutServiceImpl.checkLoginStatusViolation(username);
        centralTweetDaoImpl = new CentralTweetDaoImpl();
        boolean isUserValid = true;
        ViewGenerator.globalMenuGenerator("PASSWORD RESET MENU");
        ViewGenerator.globalMenuGenerator("PLEASE PROVIDE YOUR OLD PASSWORD");
        String oldPassword = TweetAppUtils.reader("Old Password");
        ViewGenerator.globalMenuGenerator("PLEASE PROVIDE NEW PASSWORD");
        String newPassword = TweetAppUtils.reader("New Password");
        isUserValid = centralTweetDaoImpl.userLoginCheck(username, oldPassword);
        if(isUserValid == true){
            centralTweetDaoImpl.resetPassword(username,newPassword);
        }
        reiterativeMenu(username);
    }

    private void postTweet(int choice, String username) throws MasterException {
        try {
            logoutServiceImpl = new LogoutServiceImpl();
            logoutServiceImpl.checkLoginStatusViolation(username);
            centralTweetDaoImpl = new CentralTweetDaoImpl();
            System.out.println("ENTERED");
            ViewGenerator.globalMenuGenerator("WHAT IS IN YOU MIND");
            String tweetToSave = TweetAppUtils.reader("Tweet");
            Tweets createNewTweet = new Tweets(tweetToSave, username);
            centralTweetDaoImpl.addTweet(createNewTweet);
            reiterativeMenu(username);
        }
        catch (MasterException E){
            reiterativeMenu(username);
            throw new MasterException("POST TWEET ISSUE");
        }
    }

    private void viewAllMyTweets(int choice, String username) throws MasterException {
        try {
            logoutServiceImpl = new LogoutServiceImpl();
            logoutServiceImpl.checkLoginStatusViolation(username);
            centralTweetDaoImpl = new CentralTweetDaoImpl();
            List<Users> filteredTweets;
            List<Users> allTweets = centralTweetDaoImpl.getAllUsers();
            ViewGenerator.globalMenuGenerator("ALL MY TWEETS...");
            System.out.println();
            filteredTweets = allTweets.stream().filter((x) -> x.getEmail().equals(username)).collect(Collectors.toList());
            filteredTweets.forEach(x -> {
                        x.getTweets().forEach(y -> {
                            ViewGenerator.tweetView(y.getTweet(), x.getFirstName(), x.getEmail());
                        });
                    }
            );
            reiterativeMenu(username);
        }catch (MasterException e){
            reiterativeMenu(username);
            throw new MasterException("View All My Tweet Issue");
        }
    }

    private void viewAllUsers(int choice, String username) throws MasterException, InterruptedException {
        try{
        logoutServiceImpl = new LogoutServiceImpl();
        logoutServiceImpl.checkLoginStatusViolation(username);
        centralTweetDaoImpl = new CentralTweetDaoImpl();
        List<Users> allUsers = centralTweetDaoImpl.getAllUsers();

        ViewGenerator.globalMenuGenerator("ALL REGISTERED USER...");
            System.out.println();

        allUsers.forEach(x -> {
                    ViewGenerator.allUserView(x.getFirstName(), x.getLastName(), x.getEmail(),x.getDateOfBirth());
                    });

        reiterativeMenu(username);
        }catch(MasterException e){
            reiterativeMenu(username);
            throw new MasterException("View All Users Issue");
        }

    }

    private void viewAllTweets(int choice, String username) throws InterruptedException, MasterException {
        try {
            logoutServiceImpl = new LogoutServiceImpl();
            logoutServiceImpl.checkLoginStatusViolation(username);
            centralTweetDaoImpl = new CentralTweetDaoImpl();
            List<Users> allTweets = centralTweetDaoImpl.getAllUsers();
            ViewGenerator.globalMenuGenerator("VIEW ALL TWEETS...");
            System.out.println();
            allTweets.forEach(x -> {
                        x.getTweets().forEach(y -> {
                            ViewGenerator.tweetView(y.getTweet(), x.getFirstName(), x.getEmail());
                        });
                    }
            );
            reiterativeMenu(username);
        }catch(MasterException e){
            reiterativeMenu(username);
            throw new MasterException("View All Tweet Issue");
        }
    }

    private void logout(int choice, String username) throws MasterException {
        LogoutServiceImpl logoutServiceImpl = new LogoutServiceImpl();
        logoutServiceImpl.logoutUser(username);
    }

    private void reiterativeMenu(String username) throws MasterException {
        ViewGenerator.globalMenuGenerator("PRESS ANYKEY TO LOAD MENUS");
        TweetAppUtils.reader("Press Any Key");
        ViewGenerator.loggedInView(username);
        TweetAppController.tweetAppPromt(username);
    }

}
