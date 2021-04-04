package desico.project.web;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.LessonEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.repository.*;


public class LessonTestData {
    private String testLessonId;

    private UnitNameRepository unitNameRepository;
    private ChapterNameRepository chapterNameRepository;
    private LessonRepository lessonRepository;
    private LogRepository logRepository;

    public LessonTestData(UnitNameRepository unitNameRepository, ChapterNameRepository chapterNameRepository, LessonRepository lessonRepository, LogRepository logRepository) {

        this.unitNameRepository = unitNameRepository;
        this.chapterNameRepository = chapterNameRepository;
        this.lessonRepository = lessonRepository;
        this.logRepository = logRepository;
    }

    public void init() {
        UnitNameEntity unitNameEntity = new UnitNameEntity();
        unitNameEntity.setUnitName("unit");
        unitNameEntity = unitNameRepository.save(unitNameEntity);

        ChapterNameEntity chapterNameEntity = new ChapterNameEntity();
        chapterNameEntity.setChapterName("Chapter");
        chapterNameEntity.setUnitName(unitNameEntity);
        chapterNameEntity = chapterNameRepository.save(chapterNameEntity);

        LessonEntity lesson1 = new LessonEntity();
        lesson1.setLessonUrl("/img/lesson/lesson1.mp4").setLessonName("lessonName1").setChapterName(chapterNameEntity);
        lesson1 = lessonRepository.save(lesson1);

        LessonEntity lesson2 = new LessonEntity();
        lesson2.setLessonUrl("/img/lesson/lesson2.mp4").setLessonName("lessonName2").setChapterName(chapterNameEntity);
        lesson2 = lessonRepository.save(lesson2);

        testLessonId = lesson1.getId();
    }

        void cleanUp() {
            logRepository.deleteAll();
            lessonRepository.deleteAll();
            chapterNameRepository.deleteAll();
            unitNameRepository.deleteAll();


        }

        public String getTestLessonId() {
            return testLessonId;

        }
    }

