package desico.project.web;

import desico.project.model.binding.UnitNameAddBindingModel;
import desico.project.model.service.UnitNameServiceModel;
import desico.project.service.UnitNameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/unit")
public class UnitNameController {
    private final UnitNameService unitNameService;
    private final ModelMapper modelMapper;

    public UnitNameController(UnitNameService unitNameService, ModelMapper modelMapper) {
        this.unitNameService = unitNameService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add")
    public String add(Model model) {
        if(!model.containsAttribute("unitNameAddBindingModel")){
            model.addAttribute("unitNameAddBindingModel",new UnitNameAddBindingModel());
            model.addAttribute("unitExistsError",false);
        }
        return "unit-add";}

    @PostMapping("/add")
    public String addConfirm
            (@Valid UnitNameAddBindingModel unitNameAddBindingModel,
             BindingResult bindingResult,
             RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("unitNameAddBindingModel", unitNameAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.unitNameAddBindingModel",bindingResult);
            return "redirect:add";

        }
        if (unitNameService.unitNameExists(unitNameAddBindingModel.getUnitName())) {
            redirectAttributes.addFlashAttribute("unitNameAddBindingModel", unitNameAddBindingModel);
            redirectAttributes.addFlashAttribute("unitExistsError", true);
            return "redirect:add";
        }
        else{
            unitNameService.addUnit(modelMapper.map(unitNameAddBindingModel, UnitNameServiceModel.class));
            return "redirect:/";
        }
    }
    @GetMapping("/view")
    public String view(Model model) {

            model.addAttribute("unitNamesEnteties",unitNameService.findAll());

        return "unit-view";}
}
