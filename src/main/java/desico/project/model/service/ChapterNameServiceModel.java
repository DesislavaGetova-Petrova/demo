package desico.project.model.service;

import desico.project.model.entity.UnitNameEntity;

public class ChapterNameServiceModel {
    private String unitName;
    private String chapterName;

    public ChapterNameServiceModel() {
    }

    public String getUnitName() {
        return unitName;
    }

    public ChapterNameServiceModel setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }

    public String getChapterName() {
        return chapterName;
    }

    public ChapterNameServiceModel setChapterName(String chapterName) {
        this.chapterName = chapterName;
        return this;
    }
}
