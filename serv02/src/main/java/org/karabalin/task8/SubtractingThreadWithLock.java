package org.karabalin.task8;

import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;

public class SubtractingThreadWithLock extends Thread {

    private final Lock lock;
    private final List<Integer> integers;
    private final Random random = new Random();


    public SubtractingThreadWithLock(Lock lock, List<Integer> integers) {
        this.lock = lock;
        this.integers = integers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            lock.lock();
            try {
                if (!integers.isEmpty()) {
                    integers.remove(random.nextInt(integers.size()));
                    System.out.println("D" + i);
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
