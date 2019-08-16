#include <iostream>
#include <mysql/mysql.h>

int main() {
    if (mysql_library_init(0, NULL, NULL)) {
        puts("could not initialize MySQL client library");
        exit(1);
    }
    MYSQL mysql;
    mysql_init(&mysql);

    if (mysql_real_connect(&mysql, "localhost", "cryptdb", "cryptdb", "cryptdbtest", 3306, NULL, 0)) {
        puts("Connect to mysql successfully!");
    }
    return 0;
}