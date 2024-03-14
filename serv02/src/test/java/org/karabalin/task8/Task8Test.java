package org.karabalin.task8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.assertEquals;

class AddingThreadWithLock extends Thread {

    private final Lock lock;

    private final List<Integer> integers;
    private final Random random = new Random();

    public AddingThreadWithLock(Lock lock, List<Integer> integers) {
        this.lock = lock;
        this.integers = integers;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            lock.lock();
            try {
                synchronized (integers) {
                    System.out.println("A" + i);
                    integers.add(random.nextInt());
                    integers.notify();
                }
            } finally {
                lock.unlock();
            }
        }
    }
}

class SubtractingThreadWithLock extends Thread {

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
                synchronized (integers) {
                    if (integers.isEmpty()) {
                        try {
                            integers.wait();
                        } finally {
                            lock.unlock();
                        }
                    }
                    integers.remove(random.nextInt(integers.size()));
                    System.out.println("D" + i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }
    }
}

public class Task8Test {
    @Test
    public void eighthTask() {
        List<Integer> integers = new ArrayList<>();
        Lock lock = new ReentrantLock();

        AddingThreadWithLock addingThread = new AddingThreadWithLock(lock, integers);
        SubtractingThreadWithLock subtractingThread = new SubtractingThreadWithLock(lock, integers);
        addingThread.start();
        subtractingThread.start();
        try {
            addingThread.join();
            subtractingThread.join();
            assertEquals(0, integers.size());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
