package net.biocloud.bioservice.domain.util;

import org.hibernate.dialect.MySQLInnoDBDialect;

public class FixedMySQLInnoDBDialect extends MySQLInnoDBDialect {

    public String getTableTypeString() {
        return " engine=InnoDB";
    }
}
