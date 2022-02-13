package polsl.pl.etltest.sources.etl;

import org.springframework.stereotype.Component;
import polsl.pl.etltest.target.postgres.PostgresStudent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public interface ETLInterface {
    List<PostgresStudent> extract();

    static List<PostgresStudent> filter(List<PostgresStudent> postgresStudents, String date)
    {
        long epochDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy")).toEpochSecond(LocalTime.now(), ZoneOffset.UTC);
        return postgresStudents.stream().filter(student -> student.getGraduationDate() >= epochDate).collect(Collectors.toList());
    }
}
