package com.cryptdbattack;

import java.sql.SQLException;
import java.util.Random;

public class Main {
    // mDBA account information
    static final Account mDBAccount = new Account(Account.get_account_id(), "mDBAaccountName", "mDBAaccountPassword",
            "mDBAaccountInfo", Account.get_type(), Account.get_type2());

    public static void main(String[] args) throws SQLException {
        // database connection init
        DBService.database_init();

        // create Account functions are used for test main logic, just commit them

        // create mDBA account
        generate_and_regist_mDBA_account();

        // create and regist other normal accounts with specific number
        generate_and_regist_normal_accounts(200);

        // start attack database
        Attacker.launch();
    }

    // generate and regist other accounts
    private static void generate_and_regist_normal_accounts(int num) {
        for (int i = 0; i < num; i++) {
            Account tmp = create_normal_Account();
            DBService.register(tmp.id, tmp.username, tmp.password, tmp.info, tmp.type, tmp.type2);
        }
    }

    // create mDBA account
    private static void generate_and_regist_mDBA_account() {
        if (!DBService.find_username(mDBAccount.username))
            DBService.register(mDBAccount.id, mDBAccount.username, mDBAccount.password, mDBAccount.info,
                    mDBAccount.type, mDBAccount.type2);
        // DBService.login(mDBAccount.username, mDBAccount.password);
    }

    // create normal account
    private static Account create_normal_Account() {
        String name = generate_random_string(10);
        String password = generate_random_string(20);
        String info = generate_random_string(20);
        Account ret = new Account(Account.get_account_id(), name, password, info, Account.get_type(),
                Account.get_type2());
        return ret;
    }

    // return a string generated randomly
    private static String generate_random_string(int len) {
        String base = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm0123456789";
        String ret = new String();
        Random random = new Random();
        for (int i = 1; i <= len; i++) {
            int pos = random.nextInt(base.length());
            // ret.append(base.charAt(pos));
            ret = ret + base.charAt(pos);
        }
        return ret;
    }
}