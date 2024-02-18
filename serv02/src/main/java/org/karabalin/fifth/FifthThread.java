package org.karabalin.fifth;

import java.util.List;
import java.util.Random;

public class FifthThread extends Thread {

    private final List<Integer> integers;
    private final Random random = new Random();


    public FifthThread(List<Integer> integers) {
        this.integers = integers;
    }

    public synchronized void adding() {
        for (int i = 0; i < 10000; i++) {
            integers.add(random.nextInt());
            System.out.println(integers);
        }
    }

    public synchronized void subtracting() {
        int i = 10000;
        while (i != 0) {
            integers.remove(random.nextInt(0, i--));
        }
    }

    @Override
    public void run() {
        super.run();

        this.adding();
        System.out.println(integers.size());

        this.subtracting();
        System.out.println(integers.size());
    }
}
