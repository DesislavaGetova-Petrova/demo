package desico.project.model.binding;

import desico.project.model.entity.UnitNameEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ChapterNameAddBindingModel {
    private String unitName;
    private String chapterName;

    public ChapterNameAddBindingModel() {
    }
    @NotEmpty(message = "Полето не може да е празно!")
    public String getUnitName() {
        return unitName;
    }

    public ChapterNameAddBindingModel setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    @NotEmpty(message = "Полето не може да е празно!")
    @Size(min = 3, max = 200,message = "Наименованието трябва да съдържа повече от три символа!")
    public String getChapterName() {
        return chapterName;
    }

    public ChapterNameAddBindingModel setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }
}
