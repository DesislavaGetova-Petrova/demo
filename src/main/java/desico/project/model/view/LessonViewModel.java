package desico.project.model.view;

public class LessonViewModel {
    private String id;
    private String chapterName;
    private String lessonName;
    private String lessonUrl;

    public LessonViewModel() {
    }

    public String getId() {
        return id;
    }

    public LessonViewModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getChapterName() {
        return chapterName;
    }

    public LessonViewModel setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }

    public String getLessonName() {
        return lessonName;
    }

    public LessonViewModel setLessonName(String lessonName) {
        this.lessonName = lessonName;
        return this;
    }

    public String getLessonUrl() {
        return lessonUrl;
    }

    public LessonViewModel setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
        return this;
    }
}
