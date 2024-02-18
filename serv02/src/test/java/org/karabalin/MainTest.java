package org.karabalin;

import org.junit.Test;
import org.karabalin.forth.AddingThread;
import org.karabalin.forth.SubtractingThread;
import org.karabalin.third.MyThread1;
import org.karabalin.third.MyThread2;
import org.karabalin.third.MyThread3;

import java.util.ArrayList;
import java.util.List;

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
        addingThread.start();
        try {
            addingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(integers);

        SubtractingThread subtractingThread = new SubtractingThread(integers);
        subtractingThread.start();
        try {
            subtractingThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(integers);
    }
}