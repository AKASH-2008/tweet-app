package com.cts_techacademy.dao;

import com.cts_techacademy.entity.Tweets;
import com.cts_techacademy.entity.Users;
import com.cts_techacademy.exception.MasterException;
import org.hibernate.Session;

import java.util.List;

public interface CentralTweetDao {

    public void addTweet(Tweets newTweet) throws MasterException;
    public void resetPassword(String username, String newPassword) throws MasterException;
    public void setUserActive(String email) throws MasterException;
    public List<Users> getUserByEmail(String email) throws MasterException;
    public void setUserInActive(String email) throws MasterException;
    public Boolean userLoggedInStatusCheck(String email) throws MasterException;
    public Boolean forgotPasswordCheck(String email, String dateOfBirth) throws MasterException;
    public Boolean userLoginCheck(String email, String password) throws MasterException;
    public void killSession(Session session);
    public void saveUser(Users user) throws MasterException;
    public void updateUser(Users user) throws MasterException;
    public List<Users> getAllUsers() throws MasterException;


}
