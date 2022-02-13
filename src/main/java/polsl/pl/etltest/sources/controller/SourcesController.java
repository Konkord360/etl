package polsl.pl.etltest.sources.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import polsl.pl.etltest.sources.mongo.MongoStudent;
import polsl.pl.etltest.sources.mongo.MongoStudentRepository;
import polsl.pl.etltest.sources.mysql.MysqlExtract;
import polsl.pl.etltest.sources.mysql.MysqlStudent;
import polsl.pl.etltest.sources.mysql.MysqlStudentRepository;
import polsl.pl.etltest.sources.neo.NeoStudent;
import polsl.pl.etltest.sources.neo.NeoStudentRepository;
import polsl.pl.etltest.target.postgres.PostgresStudent;
import polsl.pl.etltest.target.postgres.PostgresStudentRepository;

import java.util.List;


@RestController
public class SourcesController
{

    MongoStudentRepository mongoStudentRepository;
    MysqlStudentRepository mysqlStudentRepository;
    NeoStudentRepository neoStudentRepository;
    PostgresStudentRepository postgresStudentRepository;

    @Autowired
    SourcesController(MongoStudentRepository mongoStudentRepository, MysqlStudentRepository mysqlStudentRepository, NeoStudentRepository neoStudentRepository, PostgresStudentRepository postgresStudentRepository)
    {
        this.mysqlStudentRepository = mysqlStudentRepository;
        this.mongoStudentRepository = mongoStudentRepository;
        this.neoStudentRepository = neoStudentRepository;
        this.postgresStudentRepository = postgresStudentRepository;
    }


    @PostMapping("/addMongoStudent")
    public ResponseEntity<MongoStudent> addMongoStudent(@RequestBody MongoStudent mongoStudent)
    {
        mongoStudentRepository.save(mongoStudent);

        return ResponseEntity.ok(mongoStudent);
    }

    @PostMapping("/addMongoStudents")
    public ResponseEntity<List<MongoStudent>> addMongoStudents(@RequestBody List<MongoStudent> mongoStudentList)
    {
       mongoStudentRepository.saveAll(mongoStudentList);
       return ResponseEntity.ok(mongoStudentList);
    }

    @PostMapping("/addMysqlStudent")
    public ResponseEntity<MysqlStudent> addMysqlStudent(@RequestBody MysqlStudent mysqlStudent)
    {
        mysqlStudentRepository.save(mysqlStudent);

        return ResponseEntity.ok(mysqlStudent);
    }

    @PostMapping("/addMysqlStudents")
    public ResponseEntity<List<MysqlStudent>> addMysqlStudents(@RequestBody List<MysqlStudent> mysqlStudentList)
    {
        mysqlStudentRepository.saveAll(mysqlStudentList);

        return ResponseEntity.ok(mysqlStudentList);
    }

    @PostMapping("/addNeoStudent")
    public ResponseEntity<NeoStudent> addNeoStudent(@RequestBody NeoStudent neoStudent)
    {
        neoStudentRepository.save(neoStudent);

        return ResponseEntity.ok(neoStudent);
    }

    @PostMapping("/addNeoStudents")
    public ResponseEntity<List<NeoStudent>> addNeoStudents(@RequestBody List<NeoStudent> neoStudents)
    {
        neoStudentRepository.saveAll(neoStudents);

        return ResponseEntity.ok(neoStudents);
    }

    @PostMapping("/addPostgresStudent")
    public ResponseEntity<PostgresStudent> addPostgresStudent(@RequestBody PostgresStudent postgresStudent)
    {
        postgresStudentRepository.save(postgresStudent);
        return ResponseEntity.ok(postgresStudent);
    }

    @PostMapping("/addPostgresStudents")
    public ResponseEntity<List<PostgresStudent>> addPostgresStudents(@RequestBody List<PostgresStudent> postgresStudents)
    {
        postgresStudentRepository.saveAll(postgresStudents);
        return ResponseEntity.ok(postgresStudents);
    }
}
