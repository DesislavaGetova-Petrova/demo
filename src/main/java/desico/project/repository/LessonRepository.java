package desico.project.repository;

import desico.project.model.entity.LessonEntity;
import desico.project.model.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity,String> {
    List<LessonEntity> findAll();
    Optional<LessonEntity> findByLessonName(String lessonName);
}
