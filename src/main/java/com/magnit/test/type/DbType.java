package com.magnit.test.type;


public enum DbType {
    MYSQL("com.mysql.cj.jdbc.Driver", "MySql"),
    POSTGRESQL("org.postgresql.Driver", "PostgreSql");

    private String driver;
    private String name;

    DbType(String driver, String name) {
        this.driver = driver;
        this.name = name;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
