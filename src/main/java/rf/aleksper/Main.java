package rf.aleksper;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import rf.aleksper.models.Course;
import rf.aleksper.service.CourseDBReader;
import rf.aleksper.service.CourseGeneration;

/*
Создайте базу данных (например, SchoolDB).
В этой базе данных создайте таблицу Courses с полями id (ключ),
title, и duration.
Настройте Hibernate для работы с вашей базой данных.
Создайте Java-класс Course, соответствующий таблице Courses,
с необходимыми аннотациями Hibernate.
Используя Hibernate, напишите код для вставки, чтения, обновления
и удаления данных в таблице Courses.
Убедитесь, что каждая операция выполняется в отдельной транзакции.
*/
public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Course course1 = CourseGeneration.create();
        Course course2 = CourseGeneration.create();
        Course course3 = CourseGeneration.create();

        System.out.println(course1);
        System.out.println(course2);
        System.out.println(course3);

        // вставка данных
        System.out.println("\nВставка данных:");
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(course1);
        session.save(course2);
        session.save(course3);
        session.getTransaction().commit();
        session.close();

        // чтение данных
        System.out.println("\nЧтение данных:");
        Session session1 = sessionFactory.getCurrentSession();
        session1.beginTransaction();
        System.out.println("Чтение всех данных:");
        CourseDBReader.loadAllData(Course.class, session1).forEach(System.out::println);
        System.out.println("Выборочное тение данных:");
        System.out.println(session1.get(Course.class, course2.getId()));
        System.out.println(session1.get(Course.class, course3.getId()));
        session1.close();

        // обновление данных
        System.out.println("\nОбновление данных:");
        Session session2 = sessionFactory.getCurrentSession();
        session2.beginTransaction();
        Course retrievedCourse = session2.get(Course.class, course2.getId());
        System.out.println(retrievedCourse);
        retrievedCourse.setTitle(CourseGeneration.create().getTitle());
        retrievedCourse.setDuration(CourseGeneration.create().getDuration());
        session2.update(retrievedCourse);
        session2.getTransaction().commit();
        session2.close();

        Session session3 = sessionFactory.getCurrentSession();
        System.out.println();
        session3.beginTransaction();
        System.out.println(session3.get(Course.class, course2.getId()));
        session3.close();
//
//        // удаление данных
        System.out.println("\nУдаление данных:");
        Session session4 = sessionFactory.getCurrentSession();
        session4.beginTransaction();
        session4.delete(retrievedCourse);
        session4.getTransaction().commit();
        session4.close();
        sessionFactory.close();
    }
}
