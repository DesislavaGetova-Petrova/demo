package desico.project.model.view;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;

public class VideoViewModel {
    private String id;
    private String chapterName;
    private String videoName;
    private String videoUrl;
    private String description;


    public VideoViewModel() {
    }

    public String getId() {
        return id;
    }

    public VideoViewModel setId(String id) {
        this.id = id;
        return this;
    }


    public String getChapterName() {
        return chapterName;
    }

    public VideoViewModel setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }

    public String getVideoName() {
        return videoName;
    }

    public VideoViewModel setVideoName(String videoName) {
        this.videoName = videoName;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public VideoViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VideoViewModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
