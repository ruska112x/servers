package org.karabalin.forth;

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
        synchronized (integers) {
            int i = 10000;
            while (i != 0) {
                integers.remove(random.nextInt(0, i--));
            }
        }
    }
}
