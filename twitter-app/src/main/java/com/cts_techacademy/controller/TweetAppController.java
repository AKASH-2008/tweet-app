package com.cts_techacademy.controller;

import com.cts_techacademy.exception.MasterException;
import com.cts_techacademy.service.LogoutServiceImpl;
import com.cts_techacademy.service.TweetAppServiceImpl;
import com.cts_techacademy.util.TweetAppUtils;
import com.cts_techacademy.view.ViewGenerator;

public class TweetAppController {

    public TweetAppController(String username) throws MasterException {
        ViewGenerator.loggedInView(username);
        tweetAppPromt(username);
    }

    public static void tweetAppPromt(String username) throws MasterException {

        String str = TweetAppUtils.reader("Choice");
        LogoutServiceImpl logoutServiceImpl = new LogoutServiceImpl();
        logoutServiceImpl.exitApplication(str);
        TweetAppServiceImpl service = new TweetAppServiceImpl();
        try {
            service.choice(str, username);
        } catch (MasterException e) {
            throw new MasterException("TWEET CONTROLLER ISSUE");
        }
    }
}
