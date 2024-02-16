package org.karabalin;

import org.junit.Test;

public class MainTest {

    @Test
    public void firstTask() {
        System.out.println(Thread.currentThread());
    }

    @Test
    public void secondTask() {
        Thread thread = new Thread(() -> {
            System.out.println("Run method from my thread");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("After sleep");
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("End");
    }

    @Test
    public void thirdTask() {
    }
}