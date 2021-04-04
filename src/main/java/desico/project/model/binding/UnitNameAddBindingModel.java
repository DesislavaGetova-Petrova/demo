package desico.project.model.binding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UnitNameAddBindingModel {
    private String unitName;

    public UnitNameAddBindingModel() {
    }
    @NotEmpty(message = "Полето не може да е празно!")
    @Size(min = 3,max = 200,message = "Наименованието трябва да съдържа повече от три символа!")
    public String getUnitName() {
        return unitName;
    }

    public UnitNameAddBindingModel setUnitName(String unitName) {
        this.unitName = unitName;
        return this;
    }
}
