package desico.project.model.service;

public class LessonServiceModel {
    private String chapterName;
    private String lessonName;
    private String lessonUrl;

    public LessonServiceModel() {
    }

    public String getChapterName() {
        return chapterName;
    }

    public LessonServiceModel setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }

    public String getLessonName() {
        return lessonName;
    }

    public LessonServiceModel setLessonName(String lessonName) {
        this.lessonName = lessonName;
        return this;
    }

    public String getLessonUrl() {
        return lessonUrl;
    }

    public LessonServiceModel setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
        return this;
    }
}