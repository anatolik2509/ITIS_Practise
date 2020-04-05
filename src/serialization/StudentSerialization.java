package serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class StudentSerialization {
    public static void write(Collection<Student> collection, String path){
        try(FileOutputStream wr = new FileOutputStream(new File(path))){
            for(Student s : collection){
                char[] name = s.getName().toCharArray();
                wr.write(name.length);
                for(char c : name){
                    wr.write(c >> 8);
                    wr.write(c);
                }
                wr.write(s.getGender() == Student.Gender.MALE ? 0 : 1);
                wr.write(s.getBirthDay());
                wr.write(s.getBirthMonth());
                wr.write(s.getBirthYear() >> 8);
                wr.write(s.getBirthYear());
                char[] group = s.getGroup().toCharArray();
                wr.write(group.length);
                for(char c : group){
                    wr.write(c >> 8);
                    wr.write(c);
                }
            }
            wr.flush();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> read(String path){
        ArrayList<Student> list = new ArrayList<>();
        try(FileInputStream in = new FileInputStream(new File(path))){
            int b;
            while ((b = in.read()) != -1){
                char[] name = new char[b];
                for(int i = 0; i < b; i++){
                    name[i] |= in.read() << 8;
                    name[i] |= in.read();
                }
                String studentName = new String(name);
                Student.Gender gender = in.read() == 0 ? Student.Gender.MALE : Student.Gender.FEMALE;
                byte birthDay = (byte)in.read();
                byte birthMonth = (byte)in.read();
                short birthYear = (short)((in.read() << 8) + in.read());
                char[] group = new char[in.read()];
                for(int i = 0; i < group.length; i++){
                    group[i] |= in.read() << 8;
                    group[i] |= in.read();
                }
                String studentGroup = new String(group);
                list.add(new Student(studentName, gender, birthDay, birthMonth, birthYear, studentGroup));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        list.add(new Student("Aaaa", Student.Gender.MALE, 1, 1, 2001, "901"));
        list.add(new Student("Bbbbb", Student.Gender.FEMALE, 3, 12, 999, "902"));
        list.add(new Student("Ccccccccc", Student.Gender.MALE, 1, 1, 2001, "11-901"));
        list.add(new Student("Ddd", Student.Gender.FEMALE, 1, 1, 2010, "12-13-901"));
        write(list, "test.txt");
        list = read("test.txt");
        for(Student s : list) {
            System.out.println(s);
        }
    }
}
