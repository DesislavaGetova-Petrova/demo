package desico.project.repository;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.view.VideoViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity,String> {
List<VideoEntity> findByChapterName(ChapterNameEntity chapterNameEntity);
Optional<VideoEntity>findByVideoName(String videoName);
List<VideoEntity> findAll();

}
