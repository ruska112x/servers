package org.karabalin.forth;

import java.util.List;
import java.util.Random;

public class AddingThread extends Thread {

    private final List<Integer> integers;
    private final Random random = new Random();
    public AddingThread(List<Integer> integers) {
        this.integers = integers;
    }

    @Override
    public void run() {
        super.run();
        synchronized (integers) {
            for (int i = 0; i < 10000; i++) {
                integers.add(random.nextInt());
                System.out.println(integers);
            }
        }
    }
}
