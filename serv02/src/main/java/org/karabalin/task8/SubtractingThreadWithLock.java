package org.karabalin.task8;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class SubtractingThreadWithLock extends Thread {

    private final ReentrantLock lock;
    private final List<Integer> integers;
    private final Random random = new Random();


    public SubtractingThreadWithLock(ReentrantLock lock, List<Integer> integers) {
        this.lock = lock;
        this.integers = integers;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            int i = 10000;
            while (i != 0) {
                integers.remove(random.nextInt(0, i--));
            }
        } finally {
            lock.unlock();
        }
    }
}
