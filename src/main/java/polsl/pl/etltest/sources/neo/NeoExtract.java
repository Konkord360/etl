package polsl.pl.etltest.sources.neo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import polsl.pl.etltest.sources.etl.ETLInterface;
import polsl.pl.etltest.target.postgres.PostgresStudent;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class NeoExtract implements ETLInterface {
    NeoStudentRepository neoStudentRepository;

    @Autowired
    public NeoExtract(NeoStudentRepository neoStudentRepository)
    {
        this.neoStudentRepository = neoStudentRepository;
    }

    @Override
    public List<PostgresStudent> extract() {
        List<PostgresStudent> returnList = new ArrayList<>();

        neoStudentRepository.findAll().forEach(student -> {
            PostgresStudent postgresStudent = new PostgresStudent();
            postgresStudent.setAge(Integer.parseInt(student.getAge()));
            postgresStudent.setName(student.getName());
            postgresStudent.setSurname(student.getSurname());

            LocalDate graduationDate = LocalDate.parse(
                    student.getGraduationDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")
            );
            postgresStudent.setGraduationDate(graduationDate.toEpochSecond(LocalTime.now(), ZoneOffset.UTC));
            returnList.add(postgresStudent);
        });

        return returnList;
    }
}
