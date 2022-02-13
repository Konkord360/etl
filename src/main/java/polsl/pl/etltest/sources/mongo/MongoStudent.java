package polsl.pl.etltest.sources.mongo;


import org.springframework.data.mongodb.core.mapping.Document;

@Document()
public class MongoStudent {

    private String name;
    private String surname;
    private int age;
    private String graduationDate;//DD.MM.YYYY

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

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }
}
