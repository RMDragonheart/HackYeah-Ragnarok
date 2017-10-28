package com.vikings.hackaton.demo.model;

public class TestBean {

    public TestBean(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
