package com.cts_techacademy.demo;

import java.util.concurrent.*;

import com.cts_techacademy.controller.LoginController;
/**
 * Runner END Point App
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	LoginController welcome = new LoginController();
    	welcome.loginPromt();
    }
    
}
