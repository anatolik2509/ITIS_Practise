package serialization;

import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    public enum Gender{
        MALE,
        FEMALE;
    }
    private Gender gender;
    private byte birthDay;
    private byte birthMonth;
    private short birthYear;
    private String group;

    public Student(String name, Gender gender, byte birthDay, byte birthMonth, short birthYear, String group) {
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.birthMonth = birthMonth;
        this.birthYear = birthYear;
        this.group = group;
    }

    public Student(String name, Gender gender, int birthDay, int birthMonth, int birthYear, String group){
        this(name, gender, (byte)birthDay, (byte)birthMonth, (short)birthYear, group);
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public byte getBirthDay() {
        return birthDay;
    }

    public byte getBirthMonth() {
        return birthMonth;
    }

    public short getBirthYear() {
        return birthYear;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", birthDay=" + birthDay +
                ", birthMonth=" + birthMonth +
                ", birthYear=" + birthYear +
                ", group='" + group + '\'' +
                '}';
    }
}
