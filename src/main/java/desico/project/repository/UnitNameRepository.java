package desico.project.repository;

import desico.project.model.entity.UnitNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitNameRepository extends JpaRepository<UnitNameEntity,String > {

@Query("select e.unitName from UnitNameEntity  as e ")
List<String>findAllUnitNames();

List<UnitNameEntity> findAll();

Optional<UnitNameEntity> findByUnitName(String unitName);

}
