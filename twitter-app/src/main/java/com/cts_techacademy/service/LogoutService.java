package com.cts_techacademy.service;

import com.cts_techacademy.exception.MasterException;

public interface LogoutService {

    public void checkLoginStatusViolation(String username) throws MasterException;
    public void logoutUser(String username) throws MasterException;
    public void exitApplication(String choice);
}
