package desico.project.service;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.LessonEntity;
import desico.project.model.service.LessonServiceModel;
import desico.project.model.view.LessonViewModel;

import desico.project.repository.LessonRepository;
import desico.project.service.impl.LessonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LessonServiceTest {
    private LessonServiceImpl lessonService;
    @Mock
    LessonRepository mockLessonRepository;
    @Mock
    ModelMapper mockModelMapper;
    @Mock
    ChapterNameService mockChapterNameService;

    @BeforeEach
    public  void init() {
        mockModelMapper = new ModelMapper();
        lessonService=new LessonServiceImpl(mockLessonRepository,mockModelMapper,mockChapterNameService);
    }

@Test
void lessonNameExistTest(){
    LessonEntity lesson=new LessonEntity();
    lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
    lesson.setLessonName("lesson");
    lesson.setLessonUrl("lessonUrl");
    assertEquals(false,lessonService.lessonNameExists("No"));
}
    @Test
    void findAllLessonViewModelsTest(){
        LessonEntity lesson=new LessonEntity();
        lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        lesson.setLessonName("lesson");
        lesson.setLessonUrl("lessonUrl");
        List<LessonViewModel> lessonViewModels = new ArrayList<>();
        lessonViewModels.add(this.mockModelMapper.map(lesson, LessonViewModel.class));
        Mockito.when(mockLessonRepository.findAll()).thenReturn(List.of(lesson));
        Assertions.assertEquals(1,lessonService.findAllLessons().size());
    }

    @Test
    void findLessonEntityByLessonNameTest() {
        LessonEntity lesson=new LessonEntity();
        lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        lesson.setLessonName("lesson");
        lesson.setLessonUrl("lessonUrl");
        Mockito.when(mockLessonRepository.findByLessonName(lesson.getLessonName())).thenReturn(Optional.of(lesson));
        lessonService.findByLessonName(lesson.getLessonName());
        Mockito.verify(mockLessonRepository).findByLessonName(lesson.getLessonName());
    }

    @Test
    void findLessonViewModelByIdTest() {
        LessonEntity lesson=new LessonEntity();
        lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        lesson.setLessonName("lesson");
        lesson.setLessonUrl("lessonUrl");
        List<LessonViewModel> lessonViewModels = new ArrayList<>();
        lessonViewModels.add(this.mockModelMapper.map(lesson, LessonViewModel.class));
        Mockito.when(mockLessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));
        lessonService.findById(lesson.getId());
        Mockito.verify(mockLessonRepository).findById(lesson.getId());
    }
    @Test
    void deleteLessonTest(){
        LessonEntity lesson=new LessonEntity();
        lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        lesson.setLessonName("lesson");
        lesson.setLessonUrl("lessonUrl");
        Mockito.when(mockLessonRepository.findById(lesson.getId())).thenReturn(Optional.of(lesson));
        lessonService.deleteLesson(lesson.getId());
        Mockito.verify(mockLessonRepository).delete(lesson);


    }
    @Test
    public void addLessonTest() throws IOException {
        LessonEntity lesson=new LessonEntity();
        lesson.setChapterName(new ChapterNameEntity(){{setChapterName("ChapterName");}});
        lesson.setLessonName("lesson");
        lesson.setLessonUrl("lessonUrl");
        mockLessonRepository.save(lesson);
        LessonServiceModel lessonServiceModel = mockModelMapper.map(lesson, LessonServiceModel.class);
        lessonService.addLesson(lessonServiceModel);
        Mockito.verify(mockLessonRepository).save(lesson);
    }


}
