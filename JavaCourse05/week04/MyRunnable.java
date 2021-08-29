package org.geekbang.week04.c02;

public class MyRunnable implements Runnable {
        private final Task task;

        public MyRunnable(Task task) {
            this.task = task;
        }

        @Override
        public void run() {
            try {
                this.task.incr();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }