package polsl.pl.etltest.sources.etl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import polsl.pl.etltest.sources.mongo.MongoExtract;
import polsl.pl.etltest.sources.mysql.MysqlExtract;
import polsl.pl.etltest.sources.neo.NeoExtract;
import polsl.pl.etltest.target.postgres.PostgresStudent;
import polsl.pl.etltest.target.postgres.PostgresStudentRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ETL
{

}
