package desico.project.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lessons")
public class LessonEntity extends BaseEntity{
    private ChapterNameEntity chapterName;
    private String lessonName;
    private String lessonUrl;

    public LessonEntity() {
    }
    @ManyToOne
    public ChapterNameEntity getChapterName() {
        return chapterName;
    }

    public LessonEntity setChapterName(ChapterNameEntity chapterName) {
        this.chapterName = chapterName;
        return this;
    }
    @Column(name="lesson_name",nullable = false,unique = true)
    public String getLessonName() {
        return lessonName;
    }

    public LessonEntity setLessonName(String lessonName) {
        this.lessonName = lessonName;
        return this;
    }
    @Column(name="lesson_url",nullable = false,unique = true)
    public String getLessonUrl() {
        return lessonUrl;
    }

    public LessonEntity setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
        return this;
    }
}
