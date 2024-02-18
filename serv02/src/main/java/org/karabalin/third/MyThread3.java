package org.karabalin.third;

public class MyThread3 extends Thread {

    private final String name;

    public MyThread3(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Run method from " + name);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("After sleep");
    }
}
