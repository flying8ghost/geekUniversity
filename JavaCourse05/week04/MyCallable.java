package org.geekbang.week04.c02;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
        private final Task task;

        public MyCallable(Task task) {
            this.task = task;
        }

        @Override
        public Integer call() {
            try {
                this.task.incr();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.task.getValue();
        }

    }