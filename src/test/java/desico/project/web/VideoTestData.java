package desico.project.web;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.LessonEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.repository.ChapterNameRepository;
import desico.project.repository.VideoRepository;
import desico.project.repository.LogRepository;
import desico.project.repository.UnitNameRepository;

public class VideoTestData {
    private String testVideoId;
    private UnitNameRepository unitNameRepository;
    private ChapterNameRepository chapterNameRepository;
    private VideoRepository videoRepository;
    private LogRepository logRepository;

    public VideoTestData(UnitNameRepository unitNameRepository, ChapterNameRepository chapterNameRepository, VideoRepository videoRepository, LogRepository logRepository) {

        this.unitNameRepository = unitNameRepository;
        this.chapterNameRepository = chapterNameRepository;
        this.videoRepository = videoRepository;
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


        VideoEntity video1=new VideoEntity();
        video1.setVideoUrl("/img/video/video.mp4").setVideoName("video1").setChapterName(chapterNameEntity);
        video1=videoRepository.save(video1);

        VideoEntity video2=new VideoEntity();
        video2.setVideoUrl("/img/video/video2.mp4").setVideoName("video2").setChapterName(chapterNameEntity);
        video2=videoRepository.save(video2);

       testVideoId=video1.getId();
    }

    void cleanUp() {
        logRepository.deleteAll();
        videoRepository.deleteAll();
        chapterNameRepository.deleteAll();
        unitNameRepository.deleteAll();


    }

    public String getTestVideoId() {
        return testVideoId;

    }
}
