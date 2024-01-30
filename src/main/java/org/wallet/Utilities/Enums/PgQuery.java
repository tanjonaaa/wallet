package org.wallet.Utilities.Enums;

public enum PgQuery {
    SELECT("select %s from \"%s\""),
    INSERT("insert into \"%s\" (%s) values (%s) returning *"),
    UPDATE("update %s set %s where %s = ? returning *");

    public final String value;

    private PgQuery(String value){
        this.value = value;
    }
}
