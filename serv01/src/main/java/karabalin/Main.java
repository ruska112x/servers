package karabalin;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        var group = new Group(0, "MMB-104");
        System.out.println(group);
        var student = new Student(69, "Handsome", "Kuznetsov", "Alex", StudentStatus.STUDY, group);
        System.out.println(student);
        var iliev = new Teacher(1, "Petrovich", "Iliev", "Vicktor");
        System.out.println(iliev);
        var discipline = new Subject(0, "MATAH");
        System.out.println(discipline);
        var lesson = new Lesson(0, LocalDate.EPOCH, 1, iliev, group);
        System.out.println(lesson);
    }
}