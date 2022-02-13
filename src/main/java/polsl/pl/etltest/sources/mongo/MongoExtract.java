package polsl.pl.etltest.sources.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import polsl.pl.etltest.sources.etl.ETLInterface;
import polsl.pl.etltest.target.postgres.PostgresStudent;

import javax.persistence.Access;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MongoExtract implements ETLInterface
{

    MongoStudentRepository mongoStudentRepository;

    @Autowired
    public MongoExtract(MongoStudentRepository mongoStudentRepository)
    {
           this.mongoStudentRepository = mongoStudentRepository;
    }

    @Override
    public List<PostgresStudent> extract()
    {
        List<MongoStudent> mongoStudentList = mongoStudentRepository.findAll();
        List<PostgresStudent> returnList = new ArrayList<>();

        mongoStudentList.forEach(student -> {
            PostgresStudent postgresStudent = new PostgresStudent();
            postgresStudent.setAge(student.getAge());
            postgresStudent.setName(student.getName());
            postgresStudent.setSurname(student.getSurname());

            LocalDate graduationDate = LocalDate.parse(
                    student.getGraduationDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")
            );
            postgresStudent.setGraduationDate(graduationDate.toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
            returnList.add(postgresStudent);
        });

        return returnList;
    }
}
