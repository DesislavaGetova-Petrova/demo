package desico.project.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LessonAddBindingModel {
    private String chapterName;
    private String lessonName;
    private String lessonUrl;

    public LessonAddBindingModel() {
    }
    @NotEmpty
    public String getChapterName() {
        return chapterName;
    }

    public LessonAddBindingModel setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }
    @NotEmpty(message = "Полето не може да е празно!")
    @Size(min = 5, max = 20,message = "Наименованието трябва да е между 5 и 20 символа!")
    public String getLessonName() {
        return lessonName;
    }

    public LessonAddBindingModel setLessonName(String lessonName) {
        this.lessonName = lessonName;
        return this;
    }
    @NotEmpty(message = "Полето не може да е празно!")
    @Pattern(regexp ="\\/img\\/lesson\\/*.+",message = "Трябва да отговаря на модела /img/lesson/*.mp4")
    public String getLessonUrl() {
        return lessonUrl;
    }

    public LessonAddBindingModel setLessonUrl(String lessonUrl) {
        this.lessonUrl = lessonUrl;
        return this;
    }
}
