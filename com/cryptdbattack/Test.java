package com.cryptdbattack;

import java.util.Random;

public class Test {
    // mDBA account infomation
    static final Account mDBAccount = new Account(Account.get_account_id(), "mDBAaccountName", "mDBAaccountPassword",
            "mDBAaccountInfo", Account.get_type());

    public static void main(String[] args) {
        // database connection init
        LoginService.database_init();

        // create mDBA account
        create_mDBA_account();

        // create and regist other normal accounts with specific number
        generate_and_regist_accounts(99);

        //
    }

    // generate and regist other accounts
    private static void generate_and_regist_accounts(int num) {
        for (int i = 0; i < num; i++) {
            Account tmp = create_normal_Account();
            LoginService.register(tmp.id, tmp.username, tmp.password, tmp.info, tmp.type);
        }
    }

    // create mDBA account
    private static void create_mDBA_account() {
        if (!LoginService.find_username(mDBAccount.username))
            LoginService.register(mDBAccount.id, mDBAccount.username, mDBAccount.password, mDBAccount.info,
                    mDBAccount.type);
        // LoginService.login(mDBAccount.username, mDBAccount.password);
    }

    // create normal account
    private static Account create_normal_Account() {
        String name = generate_random_string(10);
        String password = generate_random_string(20);
        String info = generate_random_string(20);
        Account ret = new Account(Account.get_account_id(), name, password, info, Account.get_type());
        return ret;
    }

    // return a string generated randomly
    private static String generate_random_string(int len) {
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