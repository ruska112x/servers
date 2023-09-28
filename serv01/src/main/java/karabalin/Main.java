package karabalin;

import karabalin.validators.primitive.DateValidator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
//        var group = new Group(0L, "MMB-104");
//        System.out.println(group);
//        var student = new Student(69L, "a", "b", "c", StudentStatus.STUDY, group);
//        System.out.println(student);
//        var iliev = new Teacher(1L, "a", "b", "c");
//        System.out.println(iliev);
//        var discipline = new Subject(0L, "MATAH");
//        System.out.println(discipline);
//        var lesson = new Lesson(0L, LocalDate.EPOCH, 1, iliev, group);
//        System.out.println(lesson);

        var res = DateValidator.validateRegEx("30.02.2023");
        System.out.println(res);
    }
}