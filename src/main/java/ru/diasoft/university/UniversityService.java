package ru.diasoft.university;

import org.springframework.stereotype.Service;
import ru.diasoft.domain.Enrollee;
import ru.diasoft.domain.Graduate;
import ru.diasoft.domain.Student;

import java.util.List;

@Service
public class UniversityService {

    public Student enrollment(Enrollee enrollee) throws InterruptedException {

        System.out.println("Абитуриент " + enrollee.getName() + " подал документы");
        Thread.sleep(1 * 1000);
        System.out.println("Абитуриент " + enrollee.getName() + " зачислен");

        return new Student(enrollee.getName(), "Группа #" + (int) (Math.random() * 10));
    }

    public Student study(Student student) throws InterruptedException {

        System.out.println("Студент " + student.getName() + " начал обучение");
        Thread.sleep(2 * 1000);
        student.setMarkBook(
                List.of((int) (Math.random() * 5), (int) (Math.random() * 5), (int) (Math.random() * 5))
        );
        System.out.println("Студент " + student.getName() + " окончил курс");

        return student;
    }

    public Graduate graduation(Student student) throws InterruptedException {

        System.out.println("Студент " + student.getName() + " пришел на вручение диплома");
        Thread.sleep(1 * 1000);

        Graduate graduate = new Graduate(
                student.getName(),
                student.getMarkBook().stream().mapToInt(Integer::intValue).sum()
        );

        System.out.println("Студент " + graduate.getName() + " - выпустился со средней оценкой " + graduate.getGpa());

        return graduate;
    }

}
