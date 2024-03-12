package org.karabalin.task11;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;

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

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            executorService.submit(new DateThread(formatter, new Date()));
        }
        executorService.shutdown();
        assertTrue(executorService.isShutdown());
    }
}