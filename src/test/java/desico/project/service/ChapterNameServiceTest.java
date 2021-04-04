package desico.project.service;

import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.model.service.ChapterNameServiceModel;
import desico.project.model.service.VideoServiceModel;
import desico.project.repository.ChapterNameRepository;
import desico.project.service.impl.ChapterNameServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;



    @ExtendWith(MockitoExtension.class)
    public class ChapterNameServiceTest {

    private ChapterNameService chapterNameService;

    @Mock
    UnitNameService mockUnitNameService;
    @Mock
    ChapterNameRepository mockChapterNameRepository;
    @Mock
    ModelMapper mockModelMapper;

    public ChapterNameServiceTest() {
    }

    @BeforeEach
    public void init() {
        mockChapterNameRepository = Mockito.mock(ChapterNameRepository.class);
        mockUnitNameService = Mockito.mock(UnitNameService.class);
        mockModelMapper=new ModelMapper();
        chapterNameService = new ChapterNameServiceImpl(mockChapterNameRepository,mockUnitNameService, mockModelMapper);
        ChapterNameEntity chapterNameEntity = new ChapterNameEntity();
        chapterNameEntity.setChapterName("Name");
        chapterNameEntity.setUnitName(new UnitNameEntity(){{setUnitName("UnitName");}});
    }

        @Test
        public void findByChapterNameTest() {
        Assertions.assertThrows(
                IllegalStateException.class, () -> {
                    chapterNameService.findByChapterName("Chapter_does_not_exits");
                }
        );
    }
        @Test
        public void chapterNameExistTest() {
        ChapterNameEntity chapterNameEntity=new ChapterNameEntity();
        chapterNameEntity.setChapterName("Chapter");
        chapterNameEntity.setUnitName(new UnitNameEntity(){{setUnitName("UnitName");}});
        Mockito.when(mockChapterNameRepository.findByChapterName("Chapter")).thenReturn(Optional.of(chapterNameEntity));
        Assertions.assertEquals(true,chapterNameService.chapterNameExists("Chapter"));
    }

        @Test
        public void findAllChapterNamesTest() {
        ChapterNameEntity chapterNameEntity1 = new ChapterNameEntity();
        chapterNameEntity1.setChapterName("Name");
        chapterNameEntity1.setUnitName(new UnitNameEntity(){{setUnitName("UnitName");}});
        ChapterNameEntity chapterNameEntity2= new ChapterNameEntity();
        chapterNameEntity2.setChapterName("Name2");
        chapterNameEntity2.setUnitName(new UnitNameEntity(){{setUnitName("UnitName2");}});
        Mockito.when(mockChapterNameRepository.findAllChapterNames()).thenReturn(List.of(chapterNameEntity1.getChapterName(),chapterNameEntity2.getChapterName()));
        assertEquals(2,chapterNameService.findAllChapterNames().size());
    }
        @Test
        public void findByUnitNameTest() {
            UnitNameEntity unitNameEntity=new UnitNameEntity();
            unitNameEntity.setUnitName("Unit");
            ChapterNameEntity chapterNameEntity = new ChapterNameEntity();
            chapterNameEntity.setChapterName("Name");
            chapterNameEntity.setUnitName(unitNameEntity);
            Mockito.when(mockChapterNameRepository.findByUnitName(unitNameEntity)).thenReturn(List.of(chapterNameEntity));
            assertEquals(1,chapterNameService.findByUnitName(unitNameEntity).size());
        }
        @Test
        public void addTest() {
            UnitNameEntity unitNameEntity=new UnitNameEntity();
            unitNameEntity.setUnitName("Unit");
            ChapterNameEntity chapterNameEntity = new ChapterNameEntity();
            chapterNameEntity.setChapterName("Name");
            chapterNameEntity.setUnitName(unitNameEntity);
            mockChapterNameRepository.save(chapterNameEntity);
            ChapterNameServiceModel chapterNameServiceModel = mockModelMapper.map(chapterNameEntity, ChapterNameServiceModel.class);
            chapterNameService.add(chapterNameServiceModel);
            Mockito.verify(mockChapterNameRepository).save(chapterNameEntity);

        }



}
