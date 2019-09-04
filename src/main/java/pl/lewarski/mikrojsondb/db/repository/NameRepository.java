package pl.lewarski.mikrojsondb.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lewarski.mikrojsondb.db.entity.NameEntity;

import java.util.List;

@Repository
public interface NameRepository extends JpaRepository<NameEntity, Long> {

    List<NameEntity> findAll();
}
