package com.magnit.test.type;

public enum FileServiceType {
    XML("xml"),
    JSON("json");

    private String format;

    FileServiceType(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
