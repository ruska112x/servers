package org.karabalin.task6;

import java.util.List;
import java.util.Random;

public class SixthSubThread extends Thread {

    private final List<Integer> integers;

    private final Random random = new Random();

    public SixthSubThread(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            synchronized (integers) {
                if (integers.isEmpty()) {
                    try {
                        integers.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                integers.remove(random.nextInt(integers.size()));
                System.out.println("D" + i);
            }
        }
    }
}
