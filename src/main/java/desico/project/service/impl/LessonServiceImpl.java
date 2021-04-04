package desico.project.service.impl;

import desico.project.model.entity.LessonEntity;
import desico.project.model.service.LessonServiceModel;
import desico.project.model.view.LessonViewModel;
import desico.project.repository.LessonRepository;
import desico.project.repository.VideoRepository;
import desico.project.service.ChapterNameService;
import desico.project.service.LessonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapper modelMapper;
    private final ChapterNameService chapterNameService;

    public LessonServiceImpl(LessonRepository lessonRepository, ModelMapper modelMapper, ChapterNameService chapterNameService) {
        this.lessonRepository = lessonRepository;
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
    }

    @Override
    public void addLesson(LessonServiceModel lessonServiceModel) throws IOException {
        LessonEntity lessonEntity=this.modelMapper.map(lessonServiceModel,LessonEntity.class);
        lessonEntity.setChapterName(this.chapterNameService.findByChapterName(lessonServiceModel.getChapterName()));
        this.lessonRepository.saveAndFlush(lessonEntity);
    }


    @Override
    public List<LessonViewModel> findAllLessons() {
        return lessonRepository.findAll().stream()
                .map(lessonEntity -> {
                    LessonViewModel lessonViewModel=modelMapper
                            .map(lessonEntity,LessonViewModel.class);
                    lessonViewModel.setChapterName(lessonEntity.getChapterName().getChapterName());
                    return lessonViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteLesson(String id) {
        LessonEntity lessonEntity = this.lessonRepository.findById(id).orElse(null);
        this.lessonRepository.delete(lessonEntity);


    }

    @Override
    public boolean lessonNameExists(String lessonName) {
        return lessonRepository.findByLessonName(lessonName).isPresent();
    }



    @Override
    public LessonEntity findByLessonName(String lessonName) {
        return lessonRepository.findByLessonName(lessonName).orElseThrow(
                () -> new IllegalStateException("Няма урок с такова име."));
    }

    @Override
    public LessonViewModel findById(String id) {
        return lessonRepository
                .findById(id)
                .map(lessonEntity -> {
                    LessonViewModel lessonViewModel = modelMapper
                            .map(lessonEntity, LessonViewModel.class);
                   lessonViewModel.setChapterName(lessonEntity.getChapterName().getChapterName());
                    return lessonViewModel;
                })
                .orElseThrow(IllegalArgumentException::new);
    }
}
