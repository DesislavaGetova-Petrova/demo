package desico.project.model.entity;

import javax.persistence.*;


@Entity
@Table(name="chapters")
public class ChapterNameEntity extends BaseEntity {
    private UnitNameEntity unitName;
    private String chapterName;


    public ChapterNameEntity() {
    }
    @ManyToOne
    public UnitNameEntity getUnitName() {
        return unitName;
    }

    public ChapterNameEntity setUnitName(UnitNameEntity unitName) {
        this.unitName = unitName;
        return this;
    }
    @Column(name="chapter_name", nullable = false,unique = true)
    public String getChapterName() {
        return chapterName;
    }

    public ChapterNameEntity setChapterName(String resourceName) {
        this.chapterName = resourceName;
        return this;
    }

}
