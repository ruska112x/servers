package karabalin.entities;

import java.util.Objects;

public class Human {
    protected Long id;
    protected String patronymic;
    protected String surname;
    protected String name;

    public Human(Long id, String patronymic, String surname, String name) {
        this.id = id;
        this.patronymic = patronymic;
        this.surname = surname;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(id, human.id) && Objects.equals(patronymic, human.patronymic) && Objects.equals(surname, human.surname) && Objects.equals(name, human.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patronymic, surname, name);
    }
}
