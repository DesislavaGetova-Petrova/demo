package desico.project.web;

import desico.project.model.binding.ChapterNameAddBindingModel;
import desico.project.model.binding.UnitNameAddBindingModel;
import desico.project.model.service.ChapterNameServiceModel;
import desico.project.service.ChapterNameService;
import desico.project.service.UnitNameService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/chapter")
public class ChapterNameController {
    private final ModelMapper modelMapper;
    private final ChapterNameService chapterNameService;
    private final UnitNameService unitNameService;

    public ChapterNameController(ModelMapper modelMapper, ChapterNameService chapterNameService, UnitNameService unitNameService) {
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
        this.unitNameService = unitNameService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        if(!model.containsAttribute("chapterNameAddBindingModel")){
            model.addAttribute("chapterNameAddBindingModel",new ChapterNameAddBindingModel());
            model.addAttribute("chapterExistsError", false);
        }
        model.addAttribute("unitNames",unitNameService.findAllUnitNames());
        return "chapter-add";
    }
    @PostMapping("/add")
    public String addConfirm(@Valid ChapterNameAddBindingModel chapterNameAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes)      {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("chapterNameAddBindingModel", chapterNameAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.chapterNameAddBindingModel",bindingResult);
            return "redirect:add";
        }
        if (chapterNameService.chapterNameExists(chapterNameAddBindingModel.getChapterName())) {
            redirectAttributes.addFlashAttribute("chapterNameAddBindingModel", chapterNameAddBindingModel);
            redirectAttributes.addFlashAttribute("chapterExistsError",true );
            return "redirect:add";
        }
        this.chapterNameService.add(this.modelMapper.map(chapterNameAddBindingModel, ChapterNameServiceModel.class));
        return  "redirect:/";
    }
    @GetMapping("/view/{unitName}")
    public String view(@PathVariable String unitName, Model model) {
        model.addAttribute("chapterNameEntity",chapterNameService.findByUnitName(unitNameService.findByUnitName(unitName)));
        return "chapter-view";
    }


}
