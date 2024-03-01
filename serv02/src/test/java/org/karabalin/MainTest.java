package org.karabalin;

import org.junit.Test;
import org.karabalin.task3.MyThread1;
import org.karabalin.task3.MyThread2;
import org.karabalin.task3.MyThread3;
import org.karabalin.task4.AddingThread;
import org.karabalin.task4.SubtractingThread;
import org.karabalin.task5.FifthThread;
import org.karabalin.task6.SixthThread;
import org.karabalin.task7.PingThread;
import org.karabalin.task7.PongThread;
import org.karabalin.task8.AddingThreadWithLock;
import org.karabalin.task8.SubtractingThreadWithLock;
import org.karabalin.task9.PingPongThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

import static org.junit.Assert.assertEquals;

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
        MyThread1 myThread1 = new MyThread1("1");
        MyThread2 myThread2 = new MyThread2("2");
        MyThread3 myThread3 = new MyThread3("3");

        myThread1.start();
        myThread2.start();
        myThread3.start();

        try {
            myThread1.join();
            myThread2.join();
            myThread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("End");
    }

    @Test
    public void forthTask() {
        List<Integer> integers = new ArrayList<>();

        AddingThread addingThread = new AddingThread(integers);
        SubtractingThread subtractingThread = new SubtractingThread(integers);
        addingThread.start();
        subtractingThread.start();
        try {
            addingThread.join();
            subtractingThread.join();
            assertEquals(integers.size(), 0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void fifthTask() {
        List<Integer> integers = new ArrayList<>();

        FifthThread fifthThread = new FifthThread(integers);

        fifthThread.start();

        try {
            fifthThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sixthTask() {
        SixthThread sixthThread = new SixthThread();

        sixthThread.start();

        try {
            sixthThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void seventhTask() {
        Object lock = new Object();
        AtomicBoolean o = new AtomicBoolean(true);

        PingThread pingThread = new PingThread(lock, o);
        PongThread pongThread = new PongThread(lock, o);

        pingThread.start();
        pongThread.start();
    }

    @Test
    public void eighthTask() {
        List<Integer> integers = new ArrayList<>();
        ReentrantLock lock = new ReentrantLock();

        AddingThreadWithLock addingThread = new AddingThreadWithLock(lock, integers);
        addingThread.start();
        try {
            addingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(integers.size());

        SubtractingThreadWithLock subtractingThread = new SubtractingThreadWithLock(lock, integers);
        subtractingThread.start();
        try {
            subtractingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(integers.size());
    }

    @Test
    public void ninthTask() {
        PingPongThread pingPongThread = new PingPongThread(new ReentrantLock());

        pingPongThread.start();

        try {
            pingPongThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}