package com.spark30c.memory;

public class RamCleaner {
    public static void start() {
        Thread cleaner = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(60000); // Har 60 sec
                    System.gc(); // Smart cleanup, force nahi
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        cleaner.setDaemon(true);
        cleaner.start();
    }
}
