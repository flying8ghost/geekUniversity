package org.geekbang.week04.c02;

public class Thread01 {
    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread = new Thread(new MyRunnable(task));
        thread.start();
        Thread.sleep(3000L);
        System.out.println(task.getValue());
    }



}

