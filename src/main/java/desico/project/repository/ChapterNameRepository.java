package desico.project.repository;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterNameRepository extends JpaRepository<ChapterNameEntity,String> {

    @Query("select e.chapterName from ChapterNameEntity as e ")
    List<String> findAllChapterNames();

   List<ChapterNameEntity>findByUnitName(UnitNameEntity unitNameEntity);
   Optional<ChapterNameEntity> findByChapterName(String chapterName);











}
