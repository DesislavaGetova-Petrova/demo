package desico.project.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {


   private String textContent;
   private UserEntity userEntity;
   private VideoEntity videoEntity;
   private LocalDateTime dateTime;

    public CommentEntity() {
    }
    @Column(name = "text_content", columnDefinition = "TEXT")
    public String getTextContent() {
        return textContent;
    }

    public CommentEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }



    @ManyToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public CommentEntity setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @ManyToOne
    public VideoEntity getVideoEntity() {
        return videoEntity;
    }

    public CommentEntity setVideoEntity(VideoEntity videoEntity) {
        this.videoEntity = videoEntity;
        return this;
    }
    @Column(name = "date_time")
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public CommentEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }
}

