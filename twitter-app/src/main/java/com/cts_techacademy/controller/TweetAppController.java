package com.cts_techacademy.controller;

import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.service.LoginService;
import com.cts_techacademy.service.LogoutService;
import com.cts_techacademy.service.TweetAppService;
import com.cts_techacademy.util.TweetAppUtils;
import com.cts_techacademy.view.ViewGenerator;

public class TweetAppController {

    public TweetAppController(String username) throws MasterException {
        ViewGenerator.loggedInView(username);
        tweetAppPromt(username);
    }

    public static void tweetAppPromt(String username) throws MasterException {

        String str = TweetAppUtils.reader("Choice");
        LogoutService logoutService = new LogoutService();
        logoutService.exitApplication(str);
        TweetAppService service = new TweetAppService();
        try {
            service.choice(str, username);
        } catch (MasterException e) {
            throw new MasterException("TWEET CONTROLLER ISSUE");
        }
    }
}
