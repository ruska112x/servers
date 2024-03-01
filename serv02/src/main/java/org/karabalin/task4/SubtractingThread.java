package org.karabalin.task4;

import java.util.List;
import java.util.Random;

public class SubtractingThread extends Thread {
    private final List<Integer> integers;
    private final Random random = new Random();


    public SubtractingThread(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public void run() {
        while (!integers.isEmpty()) {
            synchronized (integers) {
                integers.remove(random.nextInt(0, integers.size()));
            }
        }
    }
}
