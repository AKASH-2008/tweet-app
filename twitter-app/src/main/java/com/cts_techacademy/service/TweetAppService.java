package com.cts_techacademy.service;

import com.cts_techacademy.exception.MasterException;

public interface TweetAppService {
    public void choice(String loginChoice, String username) throws MasterException, NumberFormatException;
}
