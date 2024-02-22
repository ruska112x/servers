package org.karabalin.task8;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class AddingThreadWithLock extends Thread {

    private final Lock lock;

    private final List<Integer> integers;
    private final Random random = new Random();

    public AddingThreadWithLock(Lock lock, List<Integer> integers) {
        this.lock = lock;
        this.integers = integers;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            for (int i = 0; i < 10000; ++i) {
                integers.add(random.nextInt());
                System.out.println(integers);
            }
        } finally {
            lock.unlock();
        }
    }
}
