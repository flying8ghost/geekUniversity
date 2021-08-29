package org.geekbang.week04.c02;

import java.util.concurrent.*;

public class Thread05 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Task task = new Task();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<?> future = executorService.submit(new MyCallable(task));
        if (!future.isDone()) {
            System.out.println("Task has not finished!");
        }
        System.out.println(future.get());
        executorService.shutdown();
    }

    static class MyCallable implements Callable<Integer> {
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

}

