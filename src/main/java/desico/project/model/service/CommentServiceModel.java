package desico.project.model.service;

import desico.project.model.entity.VideoEntity;

public class CommentServiceModel {
    private String videoEntity;
    private String textContent;


    public CommentServiceModel() {
    }

    public String getTextContent() {
        return textContent;
    }

    public CommentServiceModel setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public String getVideoEntity() {
        return videoEntity;
    }

    public CommentServiceModel setVideoEntity(String videoEntity) {
        this.videoEntity = videoEntity;
        return this;
    }
}
