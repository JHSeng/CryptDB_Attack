package com.cryptdbattack;

public class Test {
    static final Account mDBAccount = new Account("mDBAaccount", "mDBAaccount");

    public static void main(String[] args) {

        // create mDBA account
        if (!LoginService.findUsername(mDBAccount.username))
            LoginService.register(mDBAccount.username, mDBAccount.password);
        LoginService.login(mDBAccount.username, mDBAccount.password);

    }
}