package desico.project.model.service;

public class VideoServiceModel {
    private String chapterName;
    private String videoName;
    private String videoUrl;
    private String description;


    public VideoServiceModel() {
    }

    public String getChapterName() {
        return chapterName;
    }

    public VideoServiceModel setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }

    public String getVideoName() {
        return videoName;
    }

    public VideoServiceModel setVideoName(String videoName) {
        this.videoName = videoName;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public VideoServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VideoServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
