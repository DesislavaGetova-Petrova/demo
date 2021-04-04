package desico.project.service.impl;

import com.google.gson.Gson;
import desico.project.model.entity.UnitNameEntity;
import desico.project.model.service.UnitNameServiceModel;
import desico.project.repository.UnitNameRepository;
import desico.project.service.UnitNameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class UnitNameServiceImpl implements UnitNameService {
    private final Resource unitNamesFile;
    private final Gson gson;
    private final UnitNameRepository unitNameRepository;
    private  final ModelMapper modelMapper;



    public UnitNameServiceImpl(@Value("classpath:init/unitNames.json") Resource unitNamesFile,
                               Gson gson,
                               UnitNameRepository unitNameRepository,
                               ModelMapper modelMapper) {
        this.unitNamesFile = unitNamesFile;
        this.gson = gson;
        this.unitNameRepository = unitNameRepository;
        this.modelMapper = modelMapper;


    }

    @Override
    public void addUnit(UnitNameServiceModel unitNameServiceModel) {
            unitNameRepository.save(modelMapper.map(unitNameServiceModel, UnitNameEntity.class));
    }

    @Override
    public boolean unitNameExists(String unitName) {
        return unitNameRepository.findByUnitName(unitName).isPresent();
    }

    @Override
    public void seedUnitNames() {
        if (unitNameRepository.count() == 0) {
            try {
                UnitNameEntity[] unitNameEntities =
                        gson.fromJson(Files.readString(Path.of(unitNamesFile.getURI())), UnitNameEntity[].class);
                Arrays.stream(unitNameEntities).
                        forEach(unitNameRepository::save);
            } catch (IOException e) {
                throw new IllegalStateException("Имената на разделите не могат да се заредят... :(");
            }
        }
    }

    @Override
    public UnitNameEntity findByUnitName(String unitName) {
        return unitNameRepository.findByUnitName(unitName).orElseThrow(
                () -> new IllegalStateException("Няма раздел с такова име."));
    }

    @Override
    public List<UnitNameEntity> findAll() {
        return unitNameRepository.findAll();
    }


    @Override
    public List<String> findAllUnitNames() {
        return unitNameRepository.findAllUnitNames();
    }
}
