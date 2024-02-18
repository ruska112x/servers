package org.karabalin.sixth;

import java.util.*;

public class SixthThread extends Thread {

    private final Random random = new Random();


    @Override
    public void run() {
        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < 10000; i++) {
            integers.add(random.nextInt());
            System.out.println(integers);
        }

        System.out.println(integers.size());

        int i = 10000;
        while (i != 0) {
            integers.remove(random.nextInt(0, i--));
        }

        System.out.println(integers.size());
    }
}
