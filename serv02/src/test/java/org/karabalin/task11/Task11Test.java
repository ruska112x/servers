package org.karabalin.task11;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;

class DateThread extends Thread {
    private Formatter formatter;

    public DateThread(Formatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void run() {
        Date currentDate = new Date();
        String formattedDate = formatter.format(currentDate);
        System.out.println(Thread.currentThread().getName() + ": " + formattedDate);
    }
}

public class Task11Test {
    @Test
    public void eleventhTask() {
        Formatter formatter = new Formatter("yyyy-MM-dd HH:mm:ss");

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new DateThread(formatter));
        }
        executorService.shutdown();
        assertTrue(executorService.isShutdown());
    }
}