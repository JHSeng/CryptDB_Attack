package com.cryptdbattack;

import java.util.Random;

public class Account {
    private static int numOfAccounts = 0;
    protected String username, password, info;
    protected int id, type;

    public Account(int _id, String _username, String _password, String _info, int _type) {
        id = _id;
        username = _username;
        password = _password;
        info = _info;
        type = _type;
    }

    public static int get_account_id() {
        return ++numOfAccounts;
    }

    // define 4 types of account
    public static int get_type() {
        Random random = new Random();
        return random.nextInt(4);
    }
}