package desico.project.model.service;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class VideoServiceModelCloud {
    private String chapterName;
    private String videoName;
    private MultipartFile videoUrl;
    private String description;



    public VideoServiceModelCloud() {
    }
    @NotEmpty
    public String getChapterName() {
        return chapterName;
    }

    public VideoServiceModelCloud setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }
    @NotEmpty(message = "Полето не може да е празно!")
    @Size(min = 11, max = 20,message = "Наименованието трябва да е между 11 и 20 символа!")
    public String getVideoName() {
        return videoName;
    }

    public VideoServiceModelCloud setVideoName(String videoName) {
        this.videoName = videoName;
        return this;
    }
    @NotEmpty
    public MultipartFile getVideoUrl() {
        return videoUrl;
    }

    public VideoServiceModelCloud setVideoUrl(MultipartFile videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VideoServiceModelCloud setDescription(String description) {
        this.description = description;
        return this;
    }
}
