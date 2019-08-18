package com.cryptdbattack;

public class Test {
    static final Account mDBAccount = new Account("mDBAaccountName", "mDBAaccountPassword");

    public static void main(String[] args) {
        create_mDBA_Account();

    }

    // create mDBA account
    private static void create_mDBA_Account() {
        if (!LoginService.findUsername(mDBAccount.username))
            LoginService.register(mDBAccount.username, mDBAccount.password);
        LoginService.login(mDBAccount.username, mDBAccount.password);
    }
}