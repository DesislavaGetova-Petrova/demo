package desico.project.service;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.VideoServiceModel;
import desico.project.model.service.VideoServiceModelCloud;
import desico.project.model.view.VideoViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface VideoService {
    void add(VideoServiceModel videoServiceModel);

    void addVideo(VideoServiceModelCloud videoServiceModel) throws IOException;

    boolean videoNameExists(String videoName);

    List<VideoEntity> findbyChapterName(ChapterNameEntity chapterNameEntity);

    VideoEntity findByVideoName(String videoName);

    VideoViewModel findById(String id);

    VideoEntity findEntityById(String videoId);

    List<VideoEntity> findAll();

    List<VideoViewModel> findAllVideos();

    void deleteVideo(String id);

}
