package com.svalero.biblioteca.util;

public class Constants {

    public static final String DATABASE = "practicas2";
    public static final String USERNAME = "ABENITO";
    public static final String PASSWORD = "ABENITO";


    public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    public static final String CONNECTION_STRING = "jdbc:oracle:thin:@//localhost:1521";

    public static final String DATE_PATTERN;

    static {
        DATE_PATTERN = "yyyy-MM-dd";
    }
}
