package desico.project.service;

import com.google.gson.Gson;
import desico.project.model.entity.ChapterNameEntity;
import desico.project.model.entity.UnitNameEntity;
import desico.project.model.service.UnitNameServiceModel;
import desico.project.repository.ChapterNameRepository;
import desico.project.repository.UnitNameRepository;
import desico.project.service.impl.ChapterNameServiceImpl;
import desico.project.service.impl.UnitNameServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UnitNameServiceTest {
    private UnitNameService unitNameService;

    @Mock
    UnitNameRepository mockUnitNameRepository;
    @Mock
    Resource mockResource;

    public UnitNameServiceTest() {
    }
    @BeforeEach
    public void init() {
       unitNameService=new UnitNameServiceImpl(mockResource,new Gson(),mockUnitNameRepository,new ModelMapper());
    }
    @Test
    void findAllTest() {
        UnitNameEntity unitNameEntity=new UnitNameEntity();
        unitNameEntity.setUnitName("Unit");
        Mockito.when(mockUnitNameRepository.findAll()).thenReturn(List.of(unitNameEntity));
        Assertions.assertEquals(1, unitNameService.findAll().size());

    }
    @Test
    void findAllUnitNamesTest(){
        UnitNameEntity unitNameEntity=new UnitNameEntity();
        unitNameEntity.setUnitName("Unit");
        UnitNameEntity unitNameEntity2=new UnitNameEntity();
        unitNameEntity2.setUnitName("Unit2");
        Mockito.when(mockUnitNameRepository.findAllUnitNames()).thenReturn(List.of(unitNameEntity.getUnitName(),unitNameEntity2.getUnitName()));
        Assertions.assertEquals(2, unitNameService.findAllUnitNames().size());
    }
    @Test
    public void unitNameExistTest() {
        UnitNameEntity unitNameEntity=new UnitNameEntity();
        unitNameEntity.setUnitName("Unit");
        Mockito.when(mockUnitNameRepository.findByUnitName("Unit")).thenReturn(Optional.of(unitNameEntity));
        Assertions.assertEquals(true,unitNameService.unitNameExists("Unit"));

    }
    @Test
    public void findByUnitNameTest() {
        Assertions.assertThrows(
                IllegalStateException.class, () -> {
                    unitNameService.findByUnitName("Unit_does_not_exits");
                }
        );

    }
    @Test
    public void addUnitTest() {
        UnitNameEntity unitNameEntity=new UnitNameEntity();
        unitNameEntity.setUnitName("Unit");
        mockUnitNameRepository.save(unitNameEntity);
        ModelMapper modelMapper=new ModelMapper();
        UnitNameServiceModel unitNameServiceModel = modelMapper.map(unitNameEntity, UnitNameServiceModel.class);
        unitNameService.addUnit(unitNameServiceModel);
        Mockito.verify(mockUnitNameRepository).save(unitNameEntity);
    }

    @Test
    public void seedUnitNamesTest() {
        Assertions.assertThrows(
                NullPointerException.class, () -> {
                    unitNameService.seedUnitNames();
                }
        );

    }

}
