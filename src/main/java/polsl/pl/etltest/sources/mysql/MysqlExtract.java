package polsl.pl.etltest.sources.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import polsl.pl.etltest.sources.etl.ETLInterface;
import polsl.pl.etltest.target.postgres.PostgresStudent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MysqlExtract implements ETLInterface {

    MysqlStudentRepository mysqlStudentRepository;

    @Autowired
    public MysqlExtract(MysqlStudentRepository mysqlStudentRepository)
    {
        this.mysqlStudentRepository = mysqlStudentRepository;
    }

    @Override
    public List<PostgresStudent> extract() {
        List<PostgresStudent> returnList = new ArrayList<>();

        mysqlStudentRepository.findAll().forEach(student -> {
            PostgresStudent postgresStudent = new PostgresStudent();
            postgresStudent.setAge(student.getAge());
            postgresStudent.setName(student.getName());
            postgresStudent.setSurname(student.getSurname());

            LocalDate graduationDate = LocalDate.parse(
                    student.getGraduationDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy")
            );
            postgresStudent.setGraduationDate(graduationDate.toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
            returnList.add(postgresStudent);
        });

        return returnList;
    }
}
