package com.cryptdbattack;

import com.cryptdbattack.LoginService;

public class Test {
    public static void main(String[] args) {
        LoginService.register("test123", "123");
        LoginService.login("test123", "123");
    }
}