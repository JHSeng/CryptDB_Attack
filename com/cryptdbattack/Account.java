package com.cryptdbattack;

import java.util.Random;

public class Account {
    private static int numOfAccounts = 0;
    protected String username, password, info;
    protected int id, type, type2;

    public Account(int _id, String _username, String _password, String _info, int _type, int _type2) {
        id = _id;
        username = _username;
        password = _password;
        info = _info;
        type = _type;
        type2 = _type2;
    }

    public static int get_account_id() {
        return ++numOfAccounts;
    }

    // define 4 types of account
    public static int get_type() {
        Random random = new Random();
        int[] rnd = { 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3 };
        int a = random.nextInt(20);
        return rnd[a];
    }

    public static int get_type2() {
        int[] rnd = { 0, 1, 2, 3 };
        Random random = new Random();
        int a = random.nextInt(4);
        return rnd[a];
    }
}