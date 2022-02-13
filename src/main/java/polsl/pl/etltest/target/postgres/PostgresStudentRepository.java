package polsl.pl.etltest.target.postgres;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostgresStudentRepository extends CrudRepository<PostgresStudent, Integer> {
}
