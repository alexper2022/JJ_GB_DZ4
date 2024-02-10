package rf.aleksper.models;

import javax.persistence.*;

@Entity
@Table(name = "course")
public class Course {

    private String title;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int duration;

    public Course(String title, int duration) {
        this.title = title;
        this.duration = duration;
    }

    public Course(int id, String title, int age) {
        this.id = id;
        this.title = title;
        this.duration = age;
    }

    public Course() {
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "Курс{" +
                "id=" + id +
                ", наименование='" + title + '\'' +
                ", продолжительность=" + duration +
                "мес.}";
    }
}
