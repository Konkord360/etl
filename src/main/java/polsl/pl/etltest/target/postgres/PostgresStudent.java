package polsl.pl.etltest.target.postgres;

import javax.persistence.*;

@Entity
public class PostgresStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private int age;
    private long graduationDate; //unix epoch

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(long graduationDate) {
        this.graduationDate = graduationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
