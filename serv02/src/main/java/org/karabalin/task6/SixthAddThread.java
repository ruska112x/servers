package org.karabalin.task6;

import java.util.List;
import java.util.Random;

public class SixthAddThread extends Thread {

    private final List<Integer> integers;

    private final Random random = new Random();

    public SixthAddThread(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            synchronized (integers) {
                integers.add(random.nextInt());
                System.out.println("A" + i);
                integers.notify();
            }
        }
    }
}
