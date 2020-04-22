package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JsonTest {
    public static void main(String[] args) throws IOException {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("Aaaa", Student.Gender.MALE, 1, 1, 2001, "901"));
        //list.add(new Student("Bbbbb", Student.Gender.FEMALE, 3, 12, 999, "902"));
        //list.add(new Student("Ccccccccc", Student.Gender.MALE, 1, 1, 2001, "11-901"));
        //list.add(new Student("Ddd", Student.Gender.FEMALE, 1, 1, 2010, "12-13-901"));
        StudentJsonOutputStream outputStream = new StudentJsonOutputStream(new FileOutputStream(new File("students.yaml")));
        outputStream.writeStudents(list);
        outputStream.close();
        StudentJsonInputStream inputStream = new StudentJsonInputStream(new FileInputStream(new File("students.yaml")));
        Collection<Student> c = inputStream.readStudents();
        for(Student s : c){
            System.out.println(s);
        }
        inputStream.close();
    }
}
