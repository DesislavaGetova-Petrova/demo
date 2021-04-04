package desico.project.model.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="videos")
public class VideoEntity extends BaseEntity{
    private ChapterNameEntity chapterName;
    private String videoName;
    private String videoUrl;
    private String description;
    private Set<CommentEntity> comments;


    public VideoEntity() {

    }
    @ManyToOne
    public ChapterNameEntity getChapterName() {
        return chapterName;
    }

    public VideoEntity setChapterName(ChapterNameEntity resource) {
        this.chapterName = resource;
        return this;
    }
    @Column(name="video_name",nullable = false,unique = true)
    public String getVideoName() {
        return videoName;
    }

    public VideoEntity setVideoName(String videoName) {
        this.videoName = videoName;
        return this;
    }

    @Column( name="video_url",nullable = false,unique = true)
    public String getVideoUrl() {
        return videoUrl;
    }

    public VideoEntity setVideoUrl(String video) {
        this.videoUrl = video;
        return this;
    }
    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public VideoEntity setDescription(String description) {
        this.description = description;
        return this;
    }
    @OneToMany(mappedBy = "videoEntity", fetch = FetchType.EAGER,cascade= {CascadeType.ALL})
    public Set<CommentEntity> getComments() {
        return comments;
    }

    public VideoEntity setComments(Set<CommentEntity> comments) {
        this.comments = comments;
        return this;
    }
}
