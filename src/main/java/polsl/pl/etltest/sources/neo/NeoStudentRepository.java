package polsl.pl.etltest.sources.neo;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeoStudentRepository extends Neo4jRepository<NeoStudent, Long> {
}
