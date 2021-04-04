package desico.project.service;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.LessonEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.LessonServiceModel;
import desico.project.model.service.VideoServiceModelCloud;
import desico.project.model.view.LessonViewModel;
import desico.project.model.view.VideoViewModel;

import java.io.IOException;
import java.util.List;

public interface LessonService {

    void addLesson(LessonServiceModel lessonServiceModel) throws IOException;

    List<LessonViewModel> findAllLessons();

    void deleteLesson(String id);

    boolean lessonNameExists(String lessonName);

    LessonEntity findByLessonName(String lessonName);

    LessonViewModel findById(String id);
}
