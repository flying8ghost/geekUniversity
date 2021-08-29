package org.geekbang.week04.c02;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Thread03 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable(task));
        Thread thread = new Thread(futureTask);
        thread.start();
        if (!futureTask.isDone()) {
            System.out.println("Task has not finished!");
        }
        System.out.println(futureTask.get());
    }



}

