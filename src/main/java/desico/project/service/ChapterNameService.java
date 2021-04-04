package desico.project.service;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.ChapterNameServiceModel;

import java.util.List;
import java.util.Optional;

public interface ChapterNameService {

    List<String> findAllChapterNames();

    void add(ChapterNameServiceModel chapterNameServiceModel);
    boolean chapterNameExists(String chapterName);
    List<ChapterNameEntity> findByUnitName(UnitNameEntity unitNameEntity);
    ChapterNameEntity findByChapterName(String chapterName);



}
