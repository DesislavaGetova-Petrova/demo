package desico.project.web;

import desico.project.model.binding.LessonAddBindingModel;
import desico.project.model.service.LessonServiceModel;
import desico.project.model.view.LessonViewModel;
import desico.project.service.ChapterNameService;
import desico.project.service.LessonService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
@Controller
@RequestMapping("/lesson")
public class LessonControler {

        private final LessonService lessonService;
        private final ModelMapper modelMapper;
        private final ChapterNameService chapterNameService;

        public LessonControler(LessonService lessonService, ModelMapper modelMapper, ChapterNameService chapterNameService) {
        this.lessonService = lessonService;
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
    }




        @GetMapping("/viewAll")
        public String viewAll( Model model) {
            model.addAttribute("lessonEntity",lessonService.findAllLessons());
            return "lessons-view-all";
        }




        @GetMapping("/add")
        public String add(Model model){
            if(!model.containsAttribute("lessonAddBindingModel")){
                model.addAttribute("lessonAddBindingModel",new LessonAddBindingModel());
                model.addAttribute("lessonExistsError", false);
            }
            model.addAttribute("chapterNames",chapterNameService.findAllChapterNames());
            return "lesson-add";
        }
        @PostMapping("/add")
        public String addConfirm(@Valid @ModelAttribute("lessonAddBindingModel") LessonAddBindingModel lessonAddBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) throws IOException {

            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("lessonAddBindingModel", lessonAddBindingModel);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.lessonAddBindingModel",bindingResult);
                return "redirect:add";
            }
            if (lessonService.lessonNameExists(lessonAddBindingModel.getLessonName())) {
                redirectAttributes.addFlashAttribute("lessonAddBindingModel", lessonAddBindingModel);
                redirectAttributes.addFlashAttribute("lessonExistsError", true);
                return "redirect:add";
            }
            this.lessonService.addLesson(this.modelMapper.map(lessonAddBindingModel, LessonServiceModel.class));
            return  "redirect:/";
        }



        @GetMapping("/view/{id}")
        public String viewById(@PathVariable String id,Model model){
           LessonViewModel lessonViewModel = lessonService.findById(id);
            model.addAttribute("lesson", lessonViewModel);
            return "lesson-details";
        }

        @GetMapping("/delete/{id}")
        public String deleteVideo(@PathVariable String id,Model model) {
            LessonViewModel lessonViewModel = lessonService.findById(id);
            model.addAttribute("lesson", lessonViewModel);
            this.lessonService.deleteLesson(id);
            return  "redirect:/";
        }
}
