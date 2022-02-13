package polsl.pl.etltest.sources.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MysqlStudentRepository extends CrudRepository<MysqlStudent, Integer> {

}
