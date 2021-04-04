package desico.project.web;

import desico.project.model.binding.VideoAddBindingModel;
import desico.project.model.entity.VideoEntity;
import desico.project.model.service.CommentServiceModel;
import desico.project.model.service.VideoServiceModel;
import desico.project.model.service.VideoServiceModelCloud;
import desico.project.model.view.VideoViewModel;
import desico.project.service.ChapterNameService;
import desico.project.service.CommentService;
import desico.project.service.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/video")
public class VideoController {
private final VideoService videoService;
private final ModelMapper modelMapper;
private final ChapterNameService chapterNameService;
private  final CommentService commentService;


    public VideoController(VideoService videoService, ModelMapper modelMapper, ChapterNameService chapterNameService, CommentService commentService) {
        this.videoService = videoService;
        this.modelMapper = modelMapper;
        this.chapterNameService = chapterNameService;
        this.commentService = commentService;
    }


    @GetMapping("/addLimited")
    public String addLimited(Model model){
        if(!model.containsAttribute("videoServiceModel")){
            model.addAttribute("videoServiceModel",new VideoServiceModelCloud());
            model.addAttribute("videoExistsError", false);
        }

        model.addAttribute("chapterNames",chapterNameService.findAllChapterNames());
        return "video-add-limited";
    }
    @PostMapping("/addLimited")
    public String addConfirm( @ModelAttribute("videoServiceModel") VideoServiceModelCloud videoServiceModel, BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("videoServiceModel", videoServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.videoServiceModel",bindingResult);
            return "redirect:addLimited";
        }
        if (videoService.videoNameExists(videoServiceModel.getVideoName())) {
            redirectAttributes.addFlashAttribute("videoExistsError", true);
            return "redirect:addLimited";
        }
        this.videoService.addVideo(videoServiceModel);
        return  "redirect:/";
    }
    @GetMapping("/viewAll/{chapterName}")
    public String viewAll(@PathVariable String chapterName, Model model) {
        model.addAttribute("chapterName", chapterName);
        model.addAttribute("videoEntity",videoService.findbyChapterName(chapterNameService.findByChapterName(chapterName)));
        return "chapter-video-view";
    }
    @GetMapping("/viewAll")
    public String viewAll( Model model) {
        model.addAttribute("videoEntity",videoService.findAll());
        return "videos-view-all";
    }




    @GetMapping("/add")
    public String add(Model model){
        if(!model.containsAttribute("videoAddBindingModel")){
            model.addAttribute("videoAddBindingModel",new VideoAddBindingModel());
            model.addAttribute("videoExistsError", false);
        }
        model.addAttribute("chapterNames",chapterNameService.findAllChapterNames());
        return "video-add";
    }
    @PostMapping("/add")
    public String addConfirm(@Valid @ModelAttribute("videoAddBindingModel") VideoAddBindingModel videoAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("videoAddBindingModel", videoAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.videoAddBindingModel",bindingResult);
            return "redirect:add";
        }
        if (videoService.videoNameExists(videoAddBindingModel.getVideoName())) {
            redirectAttributes.addFlashAttribute("videoAddBindingModel", videoAddBindingModel);
            redirectAttributes.addFlashAttribute("videoExistsError", true);
            return "redirect:add";
        }
        this.videoService.add(this.modelMapper.map(videoAddBindingModel, VideoServiceModel.class));
        return  "redirect:/";
    }



    @GetMapping("/view/{id}")
    public String viewById(@PathVariable String id,Model model){
        VideoViewModel videoViewModel = videoService.findById(id);
        model.addAttribute("video", videoViewModel);
        return "video-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteVideo(@PathVariable String id,Model model) {
        VideoViewModel videoViewModel = videoService.findById(id);
        model.addAttribute("video", videoViewModel);
        this.videoService.deleteVideo(id);
        return  "redirect:/video/viewAll";
    }

    @GetMapping("/comment/{id}")
    public String commentVideo(@PathVariable String id,Model model) {
        VideoEntity videoEntity = videoService.findEntityById(id);
        model.addAttribute("videoEntity", videoEntity.getVideoName());
        model.addAttribute("commentServiceModel",new CommentServiceModel());
        return "comment-add";
    }

    @PostMapping("/comment")
    public String commentCreate(@ModelAttribute("commentServiceModel") CommentServiceModel commentServiceModel ) {
        this.commentService.createComment(commentServiceModel);
        return  "redirect:/video/viewAll";
    }




}
