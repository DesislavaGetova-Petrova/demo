package desico.project.web;

import desico.project.model.view.LessonViewModel;
import desico.project.model.view.VideoViewModel;
import desico.project.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/lesson/viewAll")
@RestController
public class LessonRestController {
    private final LessonService lessonService;

    public LessonRestController(LessonService lessonService) {
        this.lessonService = lessonService;
    }


    @GetMapping("/api")
    public ResponseEntity<List<LessonViewModel>> findAll() {
        return ResponseEntity
                .ok()
                .body(lessonService.findAllLessons());
    }
}
