package polsl.pl.etltest.target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import polsl.pl.etltest.sources.etl.ETLInterface;
import polsl.pl.etltest.sources.mongo.MongoExtract;
import polsl.pl.etltest.sources.mysql.MysqlExtract;
import polsl.pl.etltest.sources.neo.NeoExtract;
import polsl.pl.etltest.target.postgres.PostgresStudent;
import polsl.pl.etltest.target.postgres.PostgresStudentRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ETLController
{
    MysqlExtract mysqlExtract;
    MongoExtract mongoExtract;
    NeoExtract neoExtract;
    PostgresStudentRepository postgresStudentRepository;

    @Autowired
    public ETLController(MysqlExtract mysqlExtract, MongoExtract mongoExtract, NeoExtract neoExtract, PostgresStudentRepository postgresStudentRepository)
    {
       this.mysqlExtract = mysqlExtract;
       this.mongoExtract = mongoExtract;
       this.neoExtract = neoExtract;
       this.postgresStudentRepository = postgresStudentRepository;
    }

    @PostMapping("/extract")
    public ResponseEntity<List<PostgresStudent>> extract(@RequestParam String database)
    {
        ETLInterface extractor = null;
        switch (database)
        {
            case "mysql":
                extractor = mysqlExtract;
                break;
            case "mongo":
                extractor = mongoExtract;
                break;
            case "neo":
                extractor = neoExtract;
                break;
            case "all":
                return ResponseEntity.ok().body(extractAll());
            default: extractor = null;

        }
        List<PostgresStudent> studentList;
        if(extractor != null)
            studentList = extractor.extract();
        else
            studentList = new ArrayList<>();

        return ResponseEntity.ok(studentList);
    }

    private List<PostgresStudent> extractAll()
    {
        List<PostgresStudent> studentList = new ArrayList<>();

        studentList.addAll(mysqlExtract.extract());
        studentList.addAll(mongoExtract.extract());
        studentList.addAll(neoExtract.extract());

        return studentList;
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PostgresStudent>> filter(@RequestParam String date)
    {
       return ResponseEntity.ok(ETLInterface.filter(extractAll(), date));
    }

    @PostMapping("/load")
    public ResponseEntity<List<PostgresStudent>> load(@RequestBody List<PostgresStudent> studentList)
    {
        postgresStudentRepository.deleteAll();
        return ResponseEntity.ok().body((List<PostgresStudent>) postgresStudentRepository.saveAll(studentList));
    }
}
