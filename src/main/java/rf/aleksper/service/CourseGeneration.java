package rf.aleksper.service;

import rf.aleksper.models.Course;

import java.util.Random;

public class CourseGeneration {
    private static final String[] COURSES_LST = new String[]{
            "JDK", "Java Core", "GIT", "DOCKER", "ООП", "Базы данных>",
            "Алгоритмы", "Junior", "Spring"
    };

    public static Course create() {
        return new Course(COURSES_LST[new Random().nextInt(COURSES_LST.length)],
                new Random().nextInt(1, 4));
    }

}
