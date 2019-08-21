package com.cryptdbattack;

public class Account {
    private static int numOfAccounts = 0;
    protected String username, password, info;
    protected int id;

    public Account(int _id, String _username, String _password, String _info) {
        id = _id;
        username = _username;
        password = _password;
        info = _info;
    }

    public static int getAccountID() {
        return ++numOfAccounts;
    }
}