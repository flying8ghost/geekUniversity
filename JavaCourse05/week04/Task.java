package org.geekbang.week04.c02;

public class Task {

    private int value;

    public int getValue() {
        return value;
    }

    public int incr() throws InterruptedException {
        Thread.sleep(2000L);
        this.value++;
        return value;
    }
}