package com.cts_techacademy.service;

import com.cts_techacademy.exception.MasterException;

public interface LoginService {

    public void choice(String loginChoice) throws MasterException, NumberFormatException;
}
