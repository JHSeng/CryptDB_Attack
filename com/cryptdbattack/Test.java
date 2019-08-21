package com.cryptdbattack;

import java.util.Random;

public class Test {
    // mDBA account infomation
    static final Account mDBAccount = new Account(Account.getAccountID(), "mDBAaccountName", "mDBAaccountPassword",
            "mDBAaccountInfo");

    public static void main(String[] args) {
        // database connection init
        LoginService.init();

        // create mDBA account
        create_mDBA_Account();

        // create other normal accounts with specific number
        generateAccounts(99);
    }

    // generate and regist other accounts
    private static void generateAccounts(int num) {
        for (int i = 0; i < num; i++) {
            Account tmp = create_normal_Account();
            LoginService.register(tmp.id, tmp.username, tmp.password, tmp.info);
        }
    }

    // create mDBA account
    private static void create_mDBA_Account() {
        if (!LoginService.findUsername(mDBAccount.username))
            LoginService.register(mDBAccount.id, mDBAccount.username, mDBAccount.password, mDBAccount.info);
        LoginService.login(mDBAccount.username, mDBAccount.password);
    }

    // create normal account
    private static Account create_normal_Account() {
        String name = generateRandomString(10);
        String password = generateRandomString(20);
        String info = generateRandomString(20);
        Account ret = new Account(Account.getAccountID(), name, password, info);
        return ret;
    }

    // return a string generated randomly
    private static String generateRandomString(int len) {
        String base = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
        StringBuffer ret = new StringBuffer();
        Random random = new Random();
        for (int i = 1; i <= len; i++) {
            int pos = random.nextInt(base.length());
            ret.append(base.charAt(pos));
        }
        return ret.toString();
    }
}