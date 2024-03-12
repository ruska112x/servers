package org.karabalin.task11;

import org.junit.Test;

import java.util.Date;

class DateThread extends Thread {
    private Formatter formatter;

    private Date date;

    public DateThread(Formatter formatter, Date date) {
        this.formatter = formatter;
        this.date = date;
    }

    @Override
    public void run() {
        String formattedDate = formatter.format(date);
        System.out.println(Thread.currentThread().getName() + ": " + formattedDate);
    }
}

public class Task11Test {
    @Test
    public void eleventhTask() {
        Formatter formatter = new Formatter("dd.MM.yyyy :: HH:mm:ss");

        Thread thread1 = new DateThread(formatter, new Date(1, 1, 1));
        Thread thread2 = new DateThread(formatter, new Date(2, 2, 2));
        Thread thread3 = new DateThread(formatter, new Date(3, 3, 3));
        Thread thread4 = new DateThread(formatter, new Date(4, 4, 4));
        Thread thread5 = new DateThread(formatter, new Date(5, 5, 5));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}