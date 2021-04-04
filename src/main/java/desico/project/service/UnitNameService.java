package desico.project.service;

import desico.project.model.entity.UnitNameEntity;
import desico.project.model.service.UnitNameServiceModel;
import java.util.List;


public interface UnitNameService {
    void addUnit(UnitNameServiceModel unitNameServiceModel);
    boolean unitNameExists(String unitName);
    void seedUnitNames();
    UnitNameEntity findByUnitName(String unitName);
    List<UnitNameEntity> findAll();
    List<String> findAllUnitNames();


}
