package org.karabalin.task11;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Formatter {
    private SimpleDateFormat dateFormat;

    public Formatter(String pattern) {
        if (pattern == null) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        dateFormat = new SimpleDateFormat(pattern);
    }

    public String format(Date date) {
        return dateFormat.format(date);
    }
}
