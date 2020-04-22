package comparators;

import java.util.Objects;

public class Student implements Comparable<Student>{
    String name;
    String familyName;

    public Student(String name, String familyName) {
        this.name = name;
        this.familyName = familyName;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    @Override
    public int compareTo(Student other) {
        if(familyName.compareTo(other.getFamilyName()) == 0){
            return name.compareTo(other.name);
        }
        else {
            return familyName.compareTo(other.getFamilyName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name) &&
                familyName.equals(student.familyName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, familyName);
    }
}
