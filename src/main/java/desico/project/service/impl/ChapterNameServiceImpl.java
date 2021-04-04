package desico.project.service.impl;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.model.service.ChapterNameServiceModel;
import desico.project.repository.ChapterNameRepository;
import desico.project.repository.UnitNameRepository;
import desico.project.service.ChapterNameService;
import desico.project.service.UnitNameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterNameServiceImpl implements ChapterNameService {
    private final ChapterNameRepository chapterNameRepository;
    private final UnitNameService unitNameService;
    private final ModelMapper modelMapper;


    public ChapterNameServiceImpl(ChapterNameRepository chapterNameRepository, UnitNameService unitNameService, ModelMapper modelMapper) {
        this.chapterNameRepository = chapterNameRepository;
        this.unitNameService = unitNameService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> findAllChapterNames() {
        return chapterNameRepository.findAllChapterNames();
    }

    @Override
    public void add(ChapterNameServiceModel chapterNameServiceModel) {
        ChapterNameEntity chapterNameEntity=this.modelMapper.map(chapterNameServiceModel,ChapterNameEntity.class);
        chapterNameEntity.setUnitName(this.unitNameService.findByUnitName(chapterNameServiceModel.getUnitName()));
        this.chapterNameRepository.saveAndFlush(chapterNameEntity);
    }

    @Override
    public boolean chapterNameExists(String chapterName) {
        return chapterNameRepository.findByChapterName(chapterName).isPresent();
    }

    @Override
    public List<ChapterNameEntity> findByUnitName(UnitNameEntity unitNameEntity) {
       return chapterNameRepository.findByUnitName(unitNameEntity);
    }

    @Override
    public ChapterNameEntity findByChapterName(String chapterName) {
        return chapterNameRepository.findByChapterName(chapterName).orElseThrow(
                () -> new IllegalStateException("Няма тема с такова име."));
    }


}
